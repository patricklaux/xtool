package com.igeeksky.xtool.core.domain;

import com.igeeksky.xtool.core.json.SimpleJSON;

import java.util.Objects;

public class Coder extends Person {

    private String id;

    private Car car;

    private Coder partner;

    public Coder() {
    }

    public Coder(String name) {
        super(name);
    }

    public Coder(String id, String name, int age) {
        super(name, age);
        this.id = id;
    }

    public Coder(String id, String name, int age, Sex sex) {
        super(name, age, sex);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coder getPartner() {
        return partner;
    }

    public void setPartner(Coder partner) {
        this.partner = partner;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coder coder)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(getId(), coder.getId()) && Objects.equals(getCar(), coder.getCar()) && Objects.equals(getPartner(), coder.getPartner());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getCar());
        result = 31 * result + Objects.hashCode(getPartner());
        return result;
    }

    @Override
    public String toString() {
        return SimpleJSON.toJSONString(this);
    }
}