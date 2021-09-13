package com.cout.shop.model.entity;

public enum RecriptStatus {
    OPEN{{
        this.id = 1;
        this.status_name = "open";
    }},
    IN_THE_PROGRESS{{
        this.id = 2;
        this.status_name = "in_the_process";
    }},
    CLOSED{{
        this.id = 3;
        this.status_name = "closed";
    }},
    PAID{{
        this.id = 4;
        this.status_name = "paid";
    }};

    int id;
    String status_name;

    public int getId() {
        return id;
    }

    public String getStatus_name() {
        return status_name;
    }
}
