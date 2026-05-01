package com.xin.java;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-01 11:03
 * @github https://github.com/xyf527
 * @copyright
 */

public class Customer {

    int id = 1001;
    String name;
    Account acct;

    {
        name = "匿名用户";
    }

    public Customer() {
        acct = new Account();
    }

    public Customer(String name) {
        this.name = name;
    }

}

class Account {

}