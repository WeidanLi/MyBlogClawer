package cn.liweidan.orika.domain;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/19 上午9:12
 * Author : Weidan
 * Version : V1.0
 */
public class CarDto {

    String brand;
    String id;
    String name;

    @Override
    public String toString() {
        return "CarDto{" +
                "brand='" + brand + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
