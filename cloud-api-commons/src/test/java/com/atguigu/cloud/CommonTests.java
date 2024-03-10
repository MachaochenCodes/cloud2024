package com.atguigu.cloud;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class CommonTests {
    @Test
    void testZonedDateTime() {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}
