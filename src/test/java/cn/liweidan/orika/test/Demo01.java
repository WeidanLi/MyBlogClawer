package cn.liweidan.orika.test;

import cn.liweidan.crawer.utils.BeanMapper;
import cn.liweidan.orika.domain.Car;
import cn.liweidan.orika.domain.User;
import cn.liweidan.orika.domain.UserDto;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Desciption:</p>
 * CreateTime : 2017/10/19 上午9:14
 * Author : Weidan
 * Version : V1.0
 */
public class Demo01 {

    public static void main(String[] args) {
        User user = new User();
        List<Car> catList = Lists.newArrayListWithCapacity(5);
        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setBrand("Brand" + i);
            car.setId("Id" + i);
            car.setName("Au" + i);
            catList.add(car);
        }
        user.setCarList(catList);
        user.setAge(10);
        user.setName("Xiaoming");

        UserDto map = BeanMapper.map(user, UserDto.class);
        System.out.println(map);

    }

}
