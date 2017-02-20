package com.dreamycity.common;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamycity.ali.dao.AliOrderDAO;
import com.dreamycity.ali.dao.ItemDAO;
import com.dreamycity.ali.model.Item;
import com.dreamycity.ali.model.AliOrder;

import java.util.Date;

import com.dreamycity.lottery.job.LotteryJob;

public class Protocol {
    private static final Logger logger = LogManager.getLogger(Protocol.class);
    final static String CRLF = "\r\n";
    final static String SP = " ";

    final static String METHOD = "Method";
    final static String LOTTERY = "LOTTERY";
    final static String ALIORDER = "ALIORDER";

    final static String STATUS = "Status";
    final static String OK = "200";
    final static String BADREQUEST = "400";

    private HashMap<String,String> requestMap;
    private HashMap<String,String> responseMap;
 
    private StringBuffer response;


    Protocol(){
        responseMap = new HashMap<String,String>();
        requestMap = new HashMap<String,String>();
        response = new StringBuffer();
    }

    private String generator(){
        response.setLength(0);
        response.append(responseMap.get(STATUS));
        return response.toString();
    }

    public String response() throws Exception{
        if(requestMap.isEmpty() || requestMap.get(METHOD)==null){
            responseMap.put(STATUS,BADREQUEST);
            return generator();
        }
        
        responseMap.put(STATUS,OK);
        logger.info(requestMap.get(METHOD));
        switch(requestMap.get(METHOD)){
            case LOTTERY:
                logger.info("Initializing");

                SchedulerFactory sf = new StdSchedulerFactory();
                Scheduler sched = sf.getScheduler();

                // Lottery Job
                JobDetail lotteryJob = newJob(LotteryJob.class).withIdentity("lottery", "dreamyCity").build();
                CronTrigger lotteryTrigger = newTrigger().withIdentity("lotteryTrigger", "dreamyCity").withSchedule(cronSchedule("00 09 * * * ?")).build();
                Date ft = sched.scheduleJob(lotteryJob, lotteryTrigger);

                logger.info(lotteryJob.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                    + lotteryTrigger.getCronExpression());

                sched.start();
                
                break;
            case ALIORDER:
                break;
            default:
                responseMap.put(STATUS,BADREQUEST);
        }
        return generator();
    }

    public String process(String request){
        try {
            requestMap.clear();
            responseMap.clear();
            if(!request.isEmpty()) {
                String[] temp = request.split(",");
                requestMap.put(METHOD,temp[0]);
                for(int index=1;index<temp.length;index++) {
                    requestMap.put("Parameter"+index,temp[index]);
                }
            }
            return response();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public void bg() {
        switch(requestMap.get(METHOD)){
            case ALIORDER:
                ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
                AliOrderDAO aliOrderDAO = (AliOrderDAO) appContext.getBean("aliOrderDAO");
                AliOrder aliOrder = new AliOrder();
                aliOrder.build("/var/www/dreamycity/uploads/alipay/"+requestMap.get("Parameter1"));
                //aliOrder.build("/Users/lily/Sites/html/uploads/alipay/"+requestMap.get("Parameter1"));
                if(aliOrderDAO.insert(aliOrder)) {
                    ItemDAO itemDAO = (ItemDAO) appContext.getBean("itemDAO");
                    itemDAO.insert(aliOrder);
                } else {
                    logger.info("Order exist!!!");
                }
                break;
        }
    }
}
