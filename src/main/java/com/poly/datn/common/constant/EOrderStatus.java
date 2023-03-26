package com.poly.datn.common.constant;

public enum EOrderStatus {
    PENDING(1,"Chờ xác nhận"), DELIVERING(2, "Đang giao"), DELIVERED(3, "Hoàn thành");

    private String name;
    private int i;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    EOrderStatus(int i, String name){
        this.name = name;
        this.i = i;
    }
}
