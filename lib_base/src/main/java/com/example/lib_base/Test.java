package com.example.lib_base;

import java.io.Serializable;

public class Test implements Serializable {
    public String name;
    public int id;

    public Test() {
    }

    public Test(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
