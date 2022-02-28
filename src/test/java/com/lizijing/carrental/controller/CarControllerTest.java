package com.lizijing.carrental.controller;

import com.lizijing.carrental.entity.vo.CarAddVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * <p> CarControllerTest </p>
 *
 * @author LiZijing
 * @date 2022/2/28
 */
@SpringBootTest
class CarControllerTest {
    @Resource
    CarController carController;

    @Test
    void carAddTest() {
        CarAddVO carAddVO = new CarAddVO();
        carAddVO.setCarNumber("苏A13356");
        carAddVO.setBrand("大众");
        carAddVO.setModel("帕萨特");
        carAddVO.setType("轿车");
        System.out.println(carController.addOne(carAddVO).toString());
    }

}