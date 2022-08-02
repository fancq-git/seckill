package com.fancq.seckill;

import com.fancq.seckill.service.Seckill;
import com.fancq.seckill.service.impl.ChromeSeckill;
import lombok.SneakyThrows;

/**
 * 入口.
 *
 * @author fancq
 * @since 2022/7/21 15:26
 */
public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        Seckill seckill = new ChromeSeckill();
        seckill.doSeckill();
    }
}
