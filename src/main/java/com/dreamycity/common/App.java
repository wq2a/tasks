package com.dreamycity.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.ServerSocket;
import java.net.Socket;

import com.dreamycity.common.JobSocket;

public class App {
	private static final Logger logger = LogManager.getLogger(App.class);

/*	public void run() throws Exception {

		logger.info("Initializing");

        
		SchedulerFactory sf = new StdSchedulerFactory();
    	Scheduler sched = sf.getScheduler();

    	// Lottery Job
    	JobDetail lotteryJob = newJob(LotteryJob.class).withIdentity("lottery", "dreamyCity").build();
    	CronTrigger lotteryTrigger = newTrigger().withIdentity("lotteryTrigger", "dreamyCity").withSchedule(cronSchedule("00 09 * * * ?"))
        	.build();
    	Date ft = sched.scheduleJob(lotteryJob, lotteryTrigger);

    	logger.info(lotteryJob.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
             + lotteryTrigger.getCronExpression());

        //..

    	sched.start();

        
	}
*/
    public static void main(String[] args) throws Exception{
    	//App app = new App();
    	//app.run();
        /*
        AliOrder aliOrder = new AliOrder();
        aliOrder.build("1870132511898926.txt");
        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        AliOrderDAO aliOrderDAO = (AliOrderDAO) appContext.getBean("aliOrderDAO");
        if(aliOrderDAO.insert(aliOrder)) {
            ItemDAO itemDAO = (ItemDAO) appContext.getBean("itemDAO");
            itemDAO.insert(aliOrder);
        }else{
            logger.info("Order exist!!!");
        }
        */
        //System.out.println(aliOrder.toString());
    
        try{
            ServerSocket jobServerSocket = new ServerSocket(9900);
            while(true){
                JobSocket c = new JobSocket(jobServerSocket.accept());
                Thread t = new Thread(c);
                t.start();
            }
        } catch(Exception e){
            logger.error(e.getMessage());
        }
        
    }
}
