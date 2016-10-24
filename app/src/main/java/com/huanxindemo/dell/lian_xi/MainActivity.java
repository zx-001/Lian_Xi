package com.huanxindemo.dell.lian_xi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.huanxindemo.dell.lian_xi.utils.My_Util_HttpUrlConnection;
import com.huanxindemo.dell.lian_xi.utils.My_Util_String;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private My_Util_HttpUrlConnection connection;
    private My_Util_String string;
    private RecyclerView rv;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Data bean = (Data) msg.obj;
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rv.setAdapter(new My_Adapter(MainActivity.this,bean));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getData();
    }
    public void init(){
        rv = (RecyclerView) findViewById(R.id.RecycleView);


    }
    public void getData(){

        string = new My_Util_String();
        connection = new My_Util_HttpUrlConnection();
        new Thread(){

            @Override
            public void run() {
                super.run();
                InputStream http = connection.Http(string.RECYCLE_VIEW);
                Gson gson = new Gson();
                Data bean = gson.fromJson(new InputStreamReader(http), Data.class);
                Message message = new Message();
                message.obj = bean;
                handler.sendMessage(message);
            }
        }.start();
    }
}
