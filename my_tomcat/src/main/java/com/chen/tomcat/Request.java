package com.chen.tomcat;

import java.io.InputStream;

/**
 * Created by CHEN on 2016/9/27.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        StringBuffer request =new StringBuffer(2048);
        int i;
        byte[] buffer=new byte[1024];
        try {
            i=input.read(buffer);
        } catch(Exception e) {
            e.printStackTrace();
            i=-1;
        }
        for(int j=0;j<i;j++) {
            request.append((char) buffer[j]);
        }
        System.out.println(request.toString());
        uri=parseUri(request.toString());// http://localhost:50000/index.jsp ->/index.jsp
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }
    public String getUri() {
        return uri;
    }
}
