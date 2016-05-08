package com.dreamycity.ali.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamycity.ali.dao.AliOrderDAO;
import com.dreamycity.ali.dao.ItemDAO;
import com.dreamycity.ali.model.Item;
import com.dreamycity.ali.model.AliOrder;


public class AliOrderJob implements Job {

    private static final Logger logger = LogManager.getLogger(AliOrderJob.class);

    public AliOrderJob() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // JobKey jobKey = context.getJobDetail().getKey();
        // logger.info(jobKey);

        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Module.xml");

        AliOrderDAO aliOrderDAO = (AliOrderDAO) appContext.getBean("aliOrderDAO");
        AliOrder aliOrder = new AliOrder();
        aliOrder.build("1870132511898926.txt");
        if(aliOrderDAO.insert(aliOrder)) {
            ItemDAO itemDAO = (ItemDAO) appContext.getBean("itemDAO");
            itemDAO.insert(aliOrder);
        } else {
            logger.info("Order exist!!!");
        }

    }
}
