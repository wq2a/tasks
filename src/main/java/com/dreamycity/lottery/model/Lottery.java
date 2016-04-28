package com.dreamycity.lottery.model;

public class Lottery {
    private int kj_id;
    private int red1;
    private int red2;
    private int red3;
    private int red4;
    private int red5;
    private int red6;
    private int blue;
    private String time;

    public Lottery(int kj_id,int red1,int red2,int red3,int red4,int red5,
        int red6,int blue,String time) {
        this.kj_id = kj_id;
        this.red1 = red1;
        this.red2 = red2;
        this.red3 = red3;
        this.red4 = red4;
        this.red5 = red5;
        this.red6 = red6;
        this.blue = blue;
        this.time = time;
    }

    public Lottery(String[] results) {
        this.kj_id = Integer.parseInt(results[0]);
        this.red1 = Integer.parseInt(results[3]);
        this.red2 = Integer.parseInt(results[4]);
        this.red3 = Integer.parseInt(results[5]);
        this.red4 = Integer.parseInt(results[6]);
        this.red5 = Integer.parseInt(results[7]);
        this.red6 = Integer.parseInt(results[8]);
        this.blue = Integer.parseInt(results[2]);
        this.time = results[1];
    }

    public void setKj_id(int kj_id) {
        this.kj_id = kj_id;
    }
    public void setRed1(int red1) {
        this.red1 = red1;
    }
    public void setRed2(int red2) {
        this.red2 = red2;
    }
    public void setRed3(int red3) {
        this.red3 = red3;
    }
    public void setRed4(int red4) {
        this.red4 = red4;
    }
    public void setRed5(int red5) {
        this.red5 = red5;
    }
    public void setRed6(int red6) {
        this.red6 = red6;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public int getKj_id() {
        return kj_id;
    }
    public int getRed1() {
        return red1;
    }
    public int getRed2() {
        return red2;
    }
    public int getRed3() {
        return red3;
    }
    public int getRed4() {
        return red4;
    }
    public int getRed5() {
        return red5;
    }
    public int getRed6() {
        return red6;
    }
    public int getBlue() {
        return blue;
    }
    public String getTime() {
        return time;
    }
    @Override
    public String toString() {
        return ""+kj_id;
    }
}
