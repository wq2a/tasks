package com.dreamycity.lottery.repository.impl;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

import com.dreamycity.lottery.repository.LotteryRepository;
import com.dreamycity.lottery.model.Lottery;

public class LotteryRepositoryImpl implements LotteryRepository {

    public String human2Timestamp(String time){
	    try {
      		DateFormat formatter;
      		formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
      		Date date = (Date) formatter.parse(time.trim());
      		java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
      		return String.valueOf(timeStampDate.getTime()/1000);
    	} catch (ParseException e) {
      		return time;
    	}
    }

    public Lottery getLottery() {
        try{
            int index = 0;
			String[] results = new String[9];
			Document doc = Jsoup.connect("http://kj.cp.ifeng.com/kaijiang/fcssq/index").get();
			Element lt = doc.getElementsByClass("kjCont").get(0);
			doc = Jsoup.parse(lt.toString());
			// id
			results[0] = doc.getElementById("lotteryResult_issueNoId").text();
			// time
			results[1] = doc.getElementById("lotteryResult_openTimeId").text();
			results[1] = human2Timestamp(results[1]);
			// blue
			results[2] = doc.getElementsByClass("kj-blue").get(0).text();
			Elements reds = doc.getElementsByClass("kj-red");
			for(Element element : reds){
		    	results[3+index] = element.text();
		    	index++;
			}
            return new Lottery(results);
        	}catch(Exception e){
            	System.out.println(e.getMessage());
            	return null;
        	}
    }
}
