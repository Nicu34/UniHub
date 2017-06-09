package com.websystique.springmvc.controller;

/**
 * Created by nicu on 6/7/2017.
 */
public class Foo {
    private String name;

    public Foo() {
    }

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
