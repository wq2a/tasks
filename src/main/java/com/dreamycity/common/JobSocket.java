package com.dreamycity.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.HashMap;
import java.util.ArrayList;

import com.dreamycity.common.Protocol;

public class JobSocket implements Runnable{
    private static final Logger logger = LogManager.getLogger(JobSocket.class);
    final static String CRLF = "\r\n";
    private Socket socket;

    Protocol protocol;

    String line = "";

    JobSocket(Socket socket){
        this.socket = socket;
    }

    public void run(){
        protocol = new Protocol();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);) {

            line=in.readLine();
            out.println(protocol.process(line));
            socket.close();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        protocol.bg();
        
    }
}
