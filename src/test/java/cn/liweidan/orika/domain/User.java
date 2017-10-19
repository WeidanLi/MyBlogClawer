package cn.liweidan.orika.domain;

import java.util.List;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/19 上午9:12
 * Author : Weidan
 * Version : V1.0
 */
public class User {
    String name;
    int age;
    List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
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
}
