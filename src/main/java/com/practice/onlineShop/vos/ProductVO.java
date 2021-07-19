package com.practice.onlineShop.vos;

import com.practice.onlineShop.enums.Currencies;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
public class ProductVO {
        private long id;
        private String code;
        private String description;
        private double price;
        private int stock;
        private boolean valid;
        private Currencies currency;


}
