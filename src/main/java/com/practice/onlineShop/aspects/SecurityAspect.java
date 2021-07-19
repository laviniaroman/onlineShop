package com.practice.onlineShop.aspects;

import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

import static com.practice.onlineShop.enums.Roles.ADMIN;
import static com.practice.onlineShop.enums.Roles.EDITOR;

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

    private boolean userIsNotAllowedToUpdateProduct(Collection<Roles> roles) {
        return (!roles.contains(ADMIN) && !roles.contains(EDITOR));
    }

}
