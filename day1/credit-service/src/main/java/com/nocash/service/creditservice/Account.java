package com.nocash.service.creditservice;

import lombok.Data;

@Data
public class Account {
    private String id;
    private Double balance;

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
