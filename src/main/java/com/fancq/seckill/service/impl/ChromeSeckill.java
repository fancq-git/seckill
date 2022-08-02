package com.fancq.seckill.service.impl;

import com.fancq.seckill.service.Seckill;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Chrome 浏览器秒杀.
 *
 * @author fancq
 * @since 2022/7/20 11:50
 */
@Slf4j
public class ChromeSeckill implements Seckill {

    private static final String CART_XPATH = "//*[@id=\"J_Order_s_101450072_1\"]/div[1]/div/div/label";

    /**
     * 秒杀.
     *
     * @throws Exception
     */
    @Override
    public void doSeckill() throws Exception {

        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");

        //设置秒杀时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSSSSSSS");
        Date date = sdf.parse("2022-07-20 14:07:00 000000000");

        // 浏览器闪退，解决方案：https://blog.csdn.net/qew110123/article/details/87708659
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");

        //1、打开浏览器
        ChromeDriver browser = new ChromeDriver(options);

        //2、输入网址
        browser.get("http://www.taobao.com");

        //3、点击登录
        if (Objects.nonNull(browser.findElement(By.linkText("亲，请登录")))) {
            browser.findElement(By.linkText("亲，请登录")).click();
        }

        //4、扫码登录
        browser.findElement(By.className("icon-qrcode")).click();
        Thread.sleep(5000);

        //5、进入购物车页面
        browser.get("https://cart.taobao.com/cart.htm");

        //6、点击选择第一个按钮
        if (Objects.nonNull(browser.findElement(By.xpath(CART_XPATH)))) {
            browser.findElement(By.xpath(CART_XPATH)).click();
        }

        while (true) {
            //当前时间
            Date now = new Date();
            log.info("当前时间: {}", sdf.format(now));
            if (now.after(date) && browser.findElement(By.linkText("结 算")).isEnabled()) {
                browser.findElement(By.linkText("结 算")).click();
                System.out.println("已结算");
                if (Objects.nonNull(browser.findElement(By.linkText("提交订单")))) {
                    browser.findElement(By.linkText("提交订单")).click();
                    System.out.println(String.format("抢购成功时间：%s", sdf.format(new Date())));
                    break;
                }
            }
        }

        Thread.sleep(5000);
    }

}
