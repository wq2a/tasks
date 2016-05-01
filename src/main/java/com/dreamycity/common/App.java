package com.dreamycity.common;

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

import java.util.Date;

import com.dreamycity.lottery.job.LotteryJob;

public class App {
	private static final Logger logger = LogManager.getLogger(App.class);

	public void run() throws Exception {

		logger.info("Initializing");

		SchedulerFactory sf = new StdSchedulerFactory();
    	Scheduler sched = sf.getScheduler();

    	/** Lottery Job **/
    	JobDetail lotteryJob = newJob(LotteryJob.class).withIdentity("lottery", "dreamyCity").build();
    	CronTrigger lotteryTrigger = newTrigger().withIdentity("lotteryTrigger", "dreamyCity").withSchedule(cronSchedule("00 09 * * * ?"))
        	.build();
    	Date ft = sched.scheduleJob(lotteryJob, lotteryTrigger);

    	logger.info(lotteryJob.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
             + lotteryTrigger.getCronExpression());

        //..

    	sched.start();
	}

    public static void main(String[] args) throws Exception{
    	App app = new App();
    	app.run();
    }
}
