package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicu on 6/7/2017.
 */
public class FooListWrapper {
    private List<Foo> fooList;

    public FooListWrapper() {
        this.fooList = new ArrayList<Foo>();
    }

    public List<Foo> getFooList() {
        return fooList;
    }

    public void setFooList(List<Foo> fooList) {
        this.fooList = fooList;
    }

    public void add(Foo foo) {
        this.fooList.add(foo);
    }
}
