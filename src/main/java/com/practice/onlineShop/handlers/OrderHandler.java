package com.practice.onlineShop.handlers;

import com.practice.onlineShop.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class OrderHandler {

    @ExceptionHandler(InvalidProductsException.class)
    public ResponseEntity<String> handleInvalidProductsException() {
        return status(BAD_REQUEST).body("Order contains no products.");
    }

    @ExceptionHandler(InvalidCustomerIdException.class)
    public ResponseEntity<String> handleInvalidCustomerIdException() {
        return status(BAD_REQUEST).body("Order with invalid user ID.");
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<String> handleInvalidProductIdException() {
        return status(BAD_REQUEST).body("Product(s) with invalid ID.");
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<String> handleNotEnoughStockException() {
        return status(BAD_REQUEST).body("Insufficient Stock for a product.");
    }

    @ExceptionHandler(InvalidOrderIdException.class)
    public ResponseEntity<String> handleInvalidOrderIdException() {
        return status(BAD_REQUEST).body("Invalid order ID.");
    }

    @ExceptionHandler(OrderAlreadyDeliveredException.class)
    public ResponseEntity<String> OrderAlreadyDeliveredException() {
        return status(BAD_REQUEST).body("Order already delivered.");
    }

    @ExceptionHandler(OrderCanceledException.class)
    public ResponseEntity<String> OrderCanceledException() {
        return status(BAD_REQUEST).body("Order is canceled.");
    }

    @ExceptionHandler(OrderNotDeliveredYetException.class)
    public ResponseEntity<String> OrderNotDeliveredYetException() {
        return status(BAD_REQUEST).body("Order is not delivered yet.");
    }


}
