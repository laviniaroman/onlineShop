package com.practice.onlineShop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.practice.onlineShop.controllers.ProductController.addProduct(..))")
    public void addProductPointcut() {
    }

/*    @Around("com.practice.onlineShop.aspects.LogAspect.addProductPointcut()")
    public void around(JoinPoint joinPoint){
        System.out.println("In around aspect");
    }*/

    @Pointcut("execution(* com.practice.onlineShop.controllers.ProductController.updateProduct(..))")
    public void updateProductPointcut() {
    }

    @Pointcut("execution(* com.practice.onlineShop.controllers.OrderController.addOrder(..))")
    public void addOrderPointcut() {
    }


    @Before("com.practice.onlineShop.aspects.LogAspect.updateProductPointcut()")
    public void beforeUpdate(JoinPoint joinPoint) {
        System.out.println("In before aspect at " + new Date() + " for doing an update");
        System.out.println("ProductVO: " + joinPoint.getArgs()[0]);
        System.out.println("The user had the ID: " + joinPoint.getArgs()[1]);
    }

    @Before("com.practice.onlineShop.aspects.LogAspect.addProductPointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("In before aspect at " + new Date());
        System.out.println("ProductVO: " + joinPoint.getArgs()[0]);
        System.out.println("The user had the ID: " + joinPoint.getArgs()[1]);
    }

    @Before("com.practice.onlineShop.aspects.LogAspect.addOrderPointcut()")
    public void beforeAddingAnOrder(JoinPoint joinPoint) {
        System.out.println("In before aspect at " + new Date() + " for doing an update");
        System.out.println("OrderVO: " + joinPoint.getArgs()[0]);
    }

    @After("com.practice.onlineShop.aspects.LogAspect.addOrderPointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("In after aspect at " + new Date());
    }

}
