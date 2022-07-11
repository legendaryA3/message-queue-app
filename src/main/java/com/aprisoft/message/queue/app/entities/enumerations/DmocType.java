package com.aprisoft.message.queue.app.entities.enumerations;

public enum DmocType {
    CARC("CARC"), CPARP("CPARP"), FAST_TRACK("Fast Track"), F_CARG("F-CARG"), S_CARG("S-CARG"),
    ARC("Adolescent Refill Club"), MMD("MMD"), MMS("MMS"), DARF("DARF"), HOME_DELIVERY("Home Delivery"),
    CCSAP("CCSAP"), Waybiil("Waybill"), CART("CART");

    private String type;

    DmocType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
