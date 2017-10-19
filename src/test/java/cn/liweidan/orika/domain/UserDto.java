package cn.liweidan.orika.domain;

import java.util.List;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/19 上午9:12
 * Author : Weidan
 * Version : V1.0
 */
public class UserDto {
    String name;
    int age;
    List<CarDto> carList;

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carList=" + carList +
                '}';
    }

    public List<CarDto> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDto> carList) {
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
