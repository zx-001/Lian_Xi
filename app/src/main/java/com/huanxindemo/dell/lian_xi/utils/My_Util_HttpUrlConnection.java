package com.huanxindemo.dell.lian_xi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dell on 2016/10/24.
 */
public class My_Util_HttpUrlConnection {

    private InputStream inputStream;
    public InputStream Http(final String path){

        try {
            URL url = new URL(path);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() ==200){
                    inputStream = connection.getInputStream();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return inputStream;

    }

}
