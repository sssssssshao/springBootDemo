package com.sfy.myproj;

public enum TakeawayOrderStatus {
    WC("5", "30", "30");

    public String orderStatus;
    public String tradeStatus;
    public String flowStatus;
    TakeawayOrderStatus(String orderStatus, String tradeStatus, String flowStatus){
        this.orderStatus = orderStatus;
        this.tradeStatus = tradeStatus;
        this.flowStatus = flowStatus;
    }

    @Override
    public String toString() {
        return orderStatus + "," + tradeStatus + "," + flowStatus;
    }

    public static void main(String[] args){
        if ("5".equals(TakeawayOrderStatus.WC.orderStatus)) {
            System.out.println(TakeawayOrderStatus.WC);
        }
    }
}
