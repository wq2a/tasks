package com.dreamycity.ali.model;

import java.net.*;
import java.io.*;

import java.lang.StringBuffer;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.ArrayList;

import com.dreamycity.ali.model.Item;

public class AliOrder {
    private String order_id;
    private String createtime;
    private String supplier;
    private String comment;
    private int is_return;
    private String price_fixed;
    private String link;
    private float order_total;
    private ArrayList<Item> items;

    public AliOrder() {
        
    }

    public AliOrder(String order_id,String supplier,String createtime,
        String link,float order_total) {
        this.order_id = order_id;
        this.supplier = supplier;
        this.createtime = createtime;
        this.link = link;
        this.order_total = order_total;
    }

    public void build(String filename){
        items = new ArrayList<Item>();
        //URL file = new URL("localhost/uploads/alipay/"+filename);
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        //try (BufferedReader br = new BufferedReader(
        //        new InputStreamReader((new URL("http://127.0.0.1/uploads/alipay/"+filename)).openStream())))
        {
            String line;
            if((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                setOrder_id(temp[0]);
                setSupplier(temp[1]);
                setCreatetime(temp[2]);
                setLink(temp[3]);
                setOrder_total(0);
            }

            while ((line = br.readLine()) != null) {
                Item item = new Item(line,getSupplier());
                items.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getOrder_id(){
        return order_id;
    }
     
    public void setOrder_id(String order_id){
        this.order_id = order_id;
    }

    public String getCreatetime(){
        return createtime;
    }
     
    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIs_return() {
        return is_return;
    }

    public void setIs_return(int is_return) {
        this.is_return = is_return;
    }

    public String getPrice_fixed() {
        return price_fixed;
    }

    public void setPrice_fixed(String price_fixed) {
        this.price_fixed = price_fixed;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public float getOrder_total() {
        return order_total;
    }

    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.order_id+"\n");
        for(Item item : items){
            sb.append(item.getName()+","+item.getCost()+","+item.getSuggest_price()+"\n");
        }
        return sb.toString();
    }
}
