package com.dreamycity.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dreamycity.lottery.dao.LotteryDAO;
import com.dreamycity.lottery.repository.LotteryRepository;
import com.dreamycity.lottery.model.Lottery;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        //
        LotteryDAO lotteryDAO = (LotteryDAO) context.getBean("lotteryDAO");
        LotteryRepository lotteryRepository = (LotteryRepository) context.getBean("lotteryRepository");
        Lottery lottery = lotteryRepository.getLottery();
        lotteryDAO.insert(lottery);
    }
}
