package com.example.vikash.testjson.event;

import com.example.vikash.testjson.model.Brand;

public class Mediator {

Brand brandobj;

    public Brand getBrandobj() {
        return brandobj;
    }

    public Mediator(Brand brandobj) {
        this.brandobj = brandobj;
    }
}
