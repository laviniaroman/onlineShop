/*
package com.practice.onlineShop.controllers;

import antlr.ASTNULLType;
import com.practice.onlineShop.entities.Address;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.repositories.UserRepository;
import com.practice.onlineShop.vos.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.Collection;

import static com.practice.onlineShop.enums.Currencies.EUR;
import static com.practice.onlineShop.enums.Roles.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ProductController productController;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Test
    public void contextLoads(){
        assertThat(productController).isNotNull();
    }

    @Test
    public void addProduct_whenUserIsAdmin_shouldStoreTheProduct(){
        User userEntity = new User();
        userEntity.setFirstname("adminFirstName");
        Collection<Roles> roles = new ArrayList<>();
        roles.add(ADMIN);
        userEntity.setRoles(roles);
        Address address = new Address();
        address.setCity("Oslo");
        address.setStreet("Kjellgata");
        address.setZipcode("123");
        address.setNumber(2L);
        userRepository.save(userEntity);

        ProductVO productVO= new ProductVO();
        productVO.setCode("productCode");
        productVO.setPrice(10);
        productVO.setCurrency(EUR);
        productVO.setDescription("productDescription");
        productVO.setValid(true);

        testRestTemplate.postForEntity("http://localhost:" + port+"/product"+userEntity.getId(), productVO, Void.class);

        Iterable<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
        Product product = products.iterator().next();
    }


}*/
