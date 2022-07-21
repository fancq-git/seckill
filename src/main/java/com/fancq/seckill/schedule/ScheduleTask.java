package com.fancq.seckill.schedule;

import com.fancq.seckill.service.Seckill;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务.
 *
 * @author fancq
 * @since 2022/7/21 10:12
 */
@Slf4j
public class ScheduleTask {

    /**
     * 运行.
     *
     * @param seckill 秒杀接口
     * @throws ParseException
     */
    public void run(Seckill seckill) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        //指定时间运行
        Date date = sdf.parse("2022-07-29 10:00:00 000000000");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n"+new Date());
                try {
                    seckill.doSeckill();
                } catch (Exception e) {
                    log.error("运行异常", e);
                }
            }
        }, date);
    }

}
