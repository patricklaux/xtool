package com.igeeksky.xtool.core.domain;

import java.util.Objects;

/**
 * @author patrick
 * @since 0.0.4 2024/6/3
 */
public class Person {

    private String name;

    private int age;

    private Sex sex;

    protected String address;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        return getAge() == person.getAge() && Objects.equals(getName(), person.getName()) && getSex() == person.getSex() && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getName());
        result = 31 * result + getAge();
        result = 31 * result + Objects.hashCode(getSex());
        result = 31 * result + Objects.hashCode(address);
        return result;
    }
}
