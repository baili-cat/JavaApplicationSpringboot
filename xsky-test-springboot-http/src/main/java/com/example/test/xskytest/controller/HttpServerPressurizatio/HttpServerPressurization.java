package com.example.test.xskytest.controller.HttpServerPressurizatio;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baili
 * @date 2023年06月12日16:25
 */
@RestController
@RequestMapping("/ServerPressurization/")
public class HttpServerPressurization {

    @ApiOperation(value = "增加cpu使用")
    @GetMapping("cpu")
    public String cpu() {
// 角度的分割
        final double SPLIT = 0.01;
        //
        // 2PI分割的次数，也就是2/0.01个，正好是一周
        final int COUNT = (int) (2 / SPLIT);
        final double PI = Math.PI;
        // 时间间隔
        final int INTERVAL = 200;
        long[] busySpan = new long[COUNT];
        long[] idleSpan = new long[COUNT];
        int half = INTERVAL / 2;
        double radian = 0.0;
        for (int i = 0; i < COUNT; i++) {
            busySpan[i] = (long) (half + (Math.sin(PI * radian) * half));
            idleSpan[i] = INTERVAL - busySpan[i];
            radian += SPLIT;
        }
        long startTime = 0;
        int j = 0;
        while (true) {
            j = j % COUNT;
            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < busySpan[j])
                ;
            try {

                //这里的if控制可以注解掉，让Thread.sleep(idleSpan[j])一直执行。
                //我这里加了if控制是因为希望Cpu一直保存在70%以上工作的效果(小于70不sleep)，If注解掉将以正弦曲线的趋势使用Cpu
                if(idleSpan[j]<70){
                    Thread.sleep(idleSpan[j]);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            j++;
        }
        }
}
