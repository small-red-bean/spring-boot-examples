package com.redbean.quartz.controller;


import com.redbean.quartz.core.JobMetaData;
import com.redbean.quartz.core.QuartzService;
import com.redbean.quartz.job.SelfDemoJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private QuartzService quartzService;

    @RequestMapping("/demo")
    public void demo() {
        JobMetaData jobMetaData = new JobMetaData(
                "Demo",
                null,
                "0 */10 * * * ?",
                null,
                true,
                SelfDemoJob.class);
        try {
            if(quartzService.exsit(jobMetaData)) {
                quartzService.update(jobMetaData);
            } else {
                quartzService.create(jobMetaData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
