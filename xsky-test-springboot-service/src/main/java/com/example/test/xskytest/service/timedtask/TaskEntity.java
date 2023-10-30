package com.example.test.xskytest.apacheDubboService.timedtask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 任务说明
     */
    private String desc;
    /**
     * cron 表达式
     */
    private String expression;
}