package com.bjpowernode.pojo;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private String price;
    private String num;
    private String description;
    private String tid;

    // 该商品所属的类别信息
    private Type type;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", num='" + num + '\'' +
                ", description='" + description + '\'' +
                ", tid='" + tid + '\'' +
                ", type=" + type +
                '}';
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Product() {
    }

    public Product(String name, String price, String num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

