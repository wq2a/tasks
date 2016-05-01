package com.dreamycity.lottery.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamycity.lottery.dao.LotteryDAO;
import com.dreamycity.lottery.repository.LotteryRepository;
import com.dreamycity.lottery.model.Lottery;

public class LotteryJob implements Job {

    private static final Logger logger = LogManager.getLogger(LotteryJob.class);

    public LotteryJob() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();
        logger.info(jobKey);

        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        LotteryDAO lotteryDAO = (LotteryDAO) appContext.getBean("lotteryDAO");
        LotteryRepository lotteryRepository = (LotteryRepository) appContext.getBean("lotteryRepository");
        Lottery lottery = lotteryRepository.getLottery();
        lotteryDAO.insert(lottery);

    }
}
