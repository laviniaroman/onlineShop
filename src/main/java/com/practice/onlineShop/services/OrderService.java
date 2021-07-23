package com.practice.onlineShop.services;

import com.practice.onlineShop.entities.Orders;
import com.practice.onlineShop.exceptions.*;
import com.practice.onlineShop.mappers.OrderMapper;
import com.practice.onlineShop.repositories.OrderRepository;
import com.practice.onlineShop.vos.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final StockService stockService;

    public void addOrder(OrderVO orderVO) throws InvalidCustomerIdException, InvalidProductsException, InvalidProductIdException, NotEnoughStockException {
        validateStock(orderVO);
        Orders order = orderMapper.toEntity(orderVO);
        orderRepository.save(order);

    }

    @Transactional
    public void deliver(Integer orderId, Long customerId) throws InvalidOrderIdException {
        System.out.println("Customer with ID " + customerId + " is in service.");
        if (orderId == null) {
            throw new InvalidOrderIdException();
        }
        Optional<Orders> orderOptional = orderRepository.findById((orderId.longValue()));
        if (!orderOptional.isPresent()) {
            throw new InvalidOrderIdException();
        }
        Orders order = orderOptional.get();
        order.setDelivered(true);
    }

    private void validateStock(OrderVO orderVO) throws NotEnoughStockException {
        Map<Integer, Integer> productsIdsToQuantityMap = orderVO.getProductsIdsToQuantity();
        Set<Integer> productIds = productsIdsToQuantityMap.keySet();
        for (Integer productId : productIds) {
            Integer quantity = productsIdsToQuantityMap.get(productId);
            boolean havingEnoughStock = stockService.isHavingEnoughStock(productId, quantity);
            if (!havingEnoughStock) {
                throw new NotEnoughStockException();
            }
        }
    }
}
