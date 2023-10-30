package com.example.test.xskytest.web;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.xskytest.cfx.client.ServerConfig;
import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.apacheDubboService.timedtask.MyScheduledTask;
import com.example.test.xskytest.apacheDubboService.timedtask.MyTaskService;
import com.example.test.xskytest.apacheDubboService.timedtask.TaskEntity;
import com.example.test.xskytest.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class TimedTaskController {

    @Autowired
    private MyScheduledTask scheduledTask;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    ServerConfig serverConfig;

    @RequestMapping(value = "/transfer/timedtask", method = {RequestMethod.GET,
            RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public String test(Model model) throws Exception {
        model.addAttribute("contextPath",serverConfig.getContextPath());
        /*反射出所有定时任务接口的实现类，用于测试页面展示方便填写*/
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.forPackages(Constants.CONTROLLER_PACKAGE_PATH);
        FilterBuilder filter = new FilterBuilder()
                .includePackage(Constants.CONTROLLER_PACKAGE_PATH);//注解扫描包
        //.excludePackage("vip.mycollege.jdk.reflect.thirdpart");//注解扫描扫描排除包
        configurationBuilder.filterInputsBy(filter);
        //添加扫描工具
        configurationBuilder.setScanners(new SubTypesScanner(), new TypeAnnotationsScanner());
/*            configurationBuilder.addScanners(new SubTypesScanner()) // 添加子类扫描工具
            configurationBuilder.addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
            configurationBuilder .addScanners(new MethodAnnotationsScanner() ) // 添加 方法注解扫描工具
            configurationBuilder.addScanners(new MethodParameterScanner() ) // 添加方法参数扫描工*/
        Reflections Reflections = new Reflections(configurationBuilder);
        Set<Class<?>> classes = Reflections.getTypesAnnotatedWith(MyTaskService.class);
        List<TaskEntity> taskServerList = new ArrayList<>();
        List<Integer> taskidList = new ArrayList<>();
        for (Class<?>  tempClass : classes) {
            Object obj =  tempClass.newInstance();
            Method jobIdMethod = tempClass.getDeclaredMethod("jobId");
            Method methodNameMethod = tempClass.getDeclaredMethod("methodName");
            if (tempClass.isAnnotationPresent(MyTaskService.class)) {
                TaskEntity  taskEntity =new TaskEntity();
                taskEntity.setTaskId((Integer) jobIdMethod.invoke(obj));
                taskEntity.setDesc((String) methodNameMethod.invoke(obj));
                taskServerList.add(taskEntity);
                taskidList.add((Integer) jobIdMethod.invoke(obj));
            }
        }

        Collections.sort(taskidList);
        Collections.sort(taskServerList, new Comparator<TaskEntity>() {
            @Override
            public int compare(TaskEntity o1, TaskEntity o2) {
                return o1.getTaskId().compareTo(o2.getTaskId());
            }
        });

        model.addAttribute("taskidList", JSONObject.toJSONString(taskidList, SerializerFeature.MapSortField));
        model.addAttribute("taskServerList",taskServerList);
        /*启动定时任务参数数组*/

        return "timedtask";
    }

    @RequestMapping(value = "/timedtask/timing", method = {RequestMethod.GET,
            RequestMethod.POST},  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTimingTask() throws Exception {
        //model.addAttribute("users","test");
        return  JSONObject.toJSONString( scheduledTask.getTiming(),
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName,
                SerializerFeature.SortField);
    }


    @RequestMapping(value = "/timedtask/startOrChangeCron", method = {RequestMethod.GET,
            RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String startOrChangeCron(HttpServletRequest httpRequest){
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        List<TaskEntity> list = new ArrayList<>();
        TaskEntity taskEntity = new TaskEntity();
        List<Integer> taskIds;
        if (parmMap.containsKey("taskId") && StringUtils.isNotEmpty(parmMap.get("taskId"))) {
            try {
                taskIds = JSONObject.parseArray(parmMap.get("taskId"),  Integer.class);
            }catch (Exception e){return "taskId 填写异常";}
        }else {
            return "taskId  null";
        }
        if (parmMap.containsKey("desc") && StringUtils.isNotEmpty(parmMap.get("desc"))) {
            taskEntity.setDesc(parmMap.get("desc"));
        }else {
            return "desc  null";
        }
        if (parmMap.containsKey("expression") && StringUtils.isNotEmpty(parmMap.get("expression"))) {
            taskEntity.setExpression(parmMap.get("expression"));
        }else {
            return "expression  null";
        }

        try {
            for (Integer i: taskIds) {
                TaskEntity tempTaskEntity = new TaskEntity();
                tempTaskEntity.setTaskId(i);
                tempTaskEntity.setDesc(taskEntity.getDesc());
                tempTaskEntity.setExpression(taskEntity.getExpression());
                list.add(tempTaskEntity);
            }
        }catch (Exception e){ return  "startOrChangeCron 执行异常";}



     /*   if (CollectionUtils.isEmpty(list)) {
            // 这里模拟存在数据库的数据
            list = Arrays.asList(
                    new TaskEntity(1, "测试1","0/1 * * * * ?") ,
                    new TaskEntity(2, "测试2","0/1 * * * * ?")
            );
        }*/
        scheduledTask.refresh(list);
        return "task任务:" + list.toString() + "已经开始运行";
    }

    @RequestMapping(value = "/timedtask/stopCron", method = {RequestMethod.GET,
            RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String stopCron(HttpServletRequest httpRequest){
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        List<TaskEntity> list =  scheduledTask.getTiming();
/*        if (CollectionUtils.isEmpty(list)) {
            // 这里模拟将要停止的cron可通过前端传来
            list = Arrays.asList(
                    new TaskEntity(1, "测试1","0/1 * * * * ?") ,
                    new TaskEntity(2, "测试2","0/1 * * * * ?")
            );
        }*/
        scheduledTask.stop(list);
        List<Integer> collect = list.stream().map(TaskEntity::getTaskId).collect(Collectors.toList());
        return "task任务:" + collect.toString() + "已经停止启动";
    }
}
