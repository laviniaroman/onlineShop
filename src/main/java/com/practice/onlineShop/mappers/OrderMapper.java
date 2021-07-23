package com.practice.onlineShop.mappers;

import com.practice.onlineShop.entities.OrderItem;
import com.practice.onlineShop.entities.Orders;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidProductIdException;
import com.practice.onlineShop.exceptions.InvalidProductsException;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.repositories.UserRepository;
import com.practice.onlineShop.vos.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public Orders toEntity(OrderVO orderVO) throws InvalidCustomerIdException, InvalidProductsException, InvalidProductIdException {
        if (orderVO == null) {
            return null;
        }
        if (orderVO.getUserId() == null) {
            throw new InvalidCustomerIdException();
        }
        if (orderVO.getProductsIdsToQuantity().keySet().isEmpty()) {
            throw new InvalidProductsException();
        }
        Orders order = new Orders();
        Optional<User> userOptional = userRepository.findById(orderVO.getUserId().longValue());

        if (!userOptional.isPresent()) {
            throw new InvalidCustomerIdException();
        }

        OrderItem orderItem = new OrderItem();
        order.setUser(userOptional.get());

        List<OrderItem> orderItemList = new ArrayList<>();
        for (Integer productId : orderVO.getProductsIdsToQuantity().keySet()) {
            Optional<Product> productOptional = productRepository.findById(productId.longValue());
            if (!productOptional.isPresent()) {
                throw new InvalidProductIdException();
            }
            orderItem.setProduct(productOptional.get());
            orderItem.setQuantity(orderVO.getProductsIdsToQuantity().get(productId));
            orderItemList.add(orderItem);
        }

        order.setOrderItems(orderItemList);
        return order;

    }
}
