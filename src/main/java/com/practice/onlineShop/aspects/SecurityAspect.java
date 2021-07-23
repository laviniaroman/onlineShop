package com.practice.onlineShop.aspects;

import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationException;
import com.practice.onlineShop.repositories.UserRepository;
import com.practice.onlineShop.vos.OrderVO;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Collection;
import java.util.Optional;

import static com.practice.onlineShop.enums.Roles.*;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAspect {
    private final UserRepository userRepository;

    @Pointcut("execution(* com.practice.onlineShop.services.ProductService.addProduct(..))")
    public void addProduct() {
    }

    @Pointcut("execution(* com.practice.onlineShop.services.ProductService.updateProduct(..))")
    public void updateProduct() {
    }

    @Pointcut("execution(* com.practice.onlineShop.services.OrderService.addOrder(..))")
    public void addOrderPointcut() {
    }

    @Pointcut("execution(* com.practice.onlineShop.services.OrderService.deliver(..))")
    public void deliverPointcut() {
    }

    @Before("com.practice.onlineShop.aspects.SecurityAspect.addProduct()")
    public void checkSecurityBeforeAddingProduct(JoinPoint joinPoint) throws InvalidCustomerIdException {
        Long customerId = (Long) joinPoint.getArgs()[1];
        Optional<User> userOptional = userRepository.findById(customerId);

        if (!userOptional.isPresent()) {
            throw new InvalidCustomerIdException();
        }

        User user = userOptional.get();
        System.out.println(customerId);

    }

    @Before("com.practice.onlineShop.aspects.SecurityAspect.updateProduct()")
    public void checkSecurityBeforeUpdatingProduct(JoinPoint joinPoint) throws InvalidCustomerIdException {
        Long customerId = (Long) joinPoint.getArgs()[1];
        Optional<User> userOptional = userRepository.findById(customerId);

        if (!userOptional.isPresent()) {
            throw new InvalidCustomerIdException();
        }

        User user = userOptional.get();
        if (userIsNotAllowedToUpdateProduct(user.getRoles())) {
            throw new InvalidCustomerIdException();
        }
    }

    @Before("com.practice.onlineShop.aspects.SecurityAspect.addOrderPointcut()")
    public void checkSecurityBeforeAddingAnOrder(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationException {
        OrderVO orderVO = (OrderVO) joinPoint.getArgs()[0];
        if (orderVO.getUserId() == null) {
            throw new InvalidCustomerIdException();
        }
        Optional<User> userOptional = userRepository.findById(orderVO.getUserId().longValue());

        if (!userOptional.isPresent()) {
            throw new InvalidCustomerIdException();
        }

        User user = userOptional.get();

        if (userIsNotAllowedToAddAnOrder(user.getRoles())) {
            throw new InvalidOperationException();
        }

    }

    @Before("com.practice.onlineShop.aspects.SecurityAspect.deliverPointcut()")
    public void checkSecurityBeforeDelivering(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationException {
        Long customerId = (Long) joinPoint.getArgs()[1];
        Optional<User> userOptional = userRepository.findById(customerId);

        if (!userOptional.isPresent()) {
            throw new InvalidCustomerIdException();
        }

        User user = userOptional.get();

        if (userIsNotAllowedToDeliver(user.getRoles())) {
            throw new InvalidOperationException();
        }
        System.out.println(customerId);

    }

    private boolean userIsNotAllowedToDeliver(Collection<Roles> roles) {
        return roles.contains(EXPEDITOR);
    }


    private boolean userIsNotAllowedToAddAnOrder(Collection<Roles> roles) {
        return !roles.contains(CLIENT);
    }

    private boolean userIsNotAllowedToUpdateProduct(Collection<Roles> roles) {
        return (!roles.contains(ADMIN) && !roles.contains(EDITOR));
    }

}
