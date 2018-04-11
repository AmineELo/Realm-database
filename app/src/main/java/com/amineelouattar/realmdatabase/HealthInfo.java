package com.amineelouattar.realmdatabase;

import io.realm.RealmObject;

public class HealthInfo extends RealmObject {

    private String gender;
    private int weight, age;

    public HealthInfo() {
    }

    public HealthInfo(String gender, int weight, int age) {
        this.gender = gender;
        this.weight = weight;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
