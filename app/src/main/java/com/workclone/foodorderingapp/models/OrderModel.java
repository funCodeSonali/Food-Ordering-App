package com.workclone.foodorderingapp.models;

public class OrderModel {
    int orderPic;
    String orderName, orderPrice , orderNumber;

    public OrderModel(){

    }

    public OrderModel(int orderPic, String orderName, String orderPrice , String orderNumber) {
        this.orderPic = orderPic;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
    }

    public int getOrderPic() {
        return orderPic;
    }

    public void setOrderPic(int orderPic) {
        this.orderPic = orderPic;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
