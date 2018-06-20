package com.feiniao.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static String sendPostHttpByStr(String urlStr,String parmStr){
        String responseStr = "";
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        OutputStream os = null;
        InputStream inStrm = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Host","sandbox-walletapi.onethingpcs.com");
            connection.setReadTimeout(300000);
            connection.setConnectTimeout(300000);

            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            os = connection.getOutputStream();
            os.write(parmStr.getBytes());
            os.flush();

            //real send request
            inStrm = connection.getInputStream();

            StringBuffer responseStrBuf = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = inStrm.read(b)) != -1;) {
                responseStrBuf.append(new String(b, 0, n));
            }

            responseStr = responseStrBuf.toString();

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(inStrm!=null){
                try {
                    inStrm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                connection.disconnect();
            }

        }
        return responseStr;
    }
}
