package com.com.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView webview;

    EditText et1;
    TextView tv1;
    ListView lv1;
    ArrayList<Weather> arrlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.city);
        tv1 = (TextView) findViewById(R.id.tv1);

        lv1 = (ListView) findViewById(R.id.lv1);
    }

    public void click(View v) {
        if(v.getId()==R.id.bt1) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        String city = et1.getText().toString().trim();
                        String path = " https://api.seniverse.com/v3/weather/now.json?key=SrvH71t8JeTOXNLJP&location=shanghai&language=zh-Hans&unit=c";

                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setReadTimeout(5000);
                        conn.setRequestMethod("GET");
                        if(conn.getResponseCode()==200) {
                            //请求成功
                            InputStream in = conn.getInputStream();
                            String str = "";
                            int len=0;
                            byte[] buffer = new byte[1024];
                            while((len=in.read(buffer))>0) {

                                str += new String(buffer,0,len);
                            }
                            //解析json
                            arrlist = MyJsonParser.getWeather(str);
                            runOnUiThread(
                                    new Runnable() {
                                        public void run() {
                                            tv1.setText(arrlist.get(0).getCity());
                                            lv1.setAdapter(new MyAdapter());
                                        }
                                    }
                            );


                        }else {
                            showContent("请求失败");
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        showContent("服务器繁忙。。。");
                    }
                }
            }.start();
        }
    }

    public void showContent(final String str) {

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return arrlist.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View vertview, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view;
            if(vertview==null) {
                //使用打气筒找到item
                view = View.inflate(getApplicationContext(), R.layout.item, null);

            }else {
                view = vertview;
            }
            // 找到相关的控件
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView text_day = (TextView) view.findViewById(R.id.text_day);
            TextView text_night = (TextView) view.findViewById(R.id.text_night);
            TextView low = (TextView) view.findViewById(R.id.low);
            TextView high = (TextView) view.findViewById(R.id.high);

            //设置相关的值
            Weather wea = arrlist.get(position);
            date.setText(wea.getDate());
            text_day.setText("白天："+wea.getText_day());
            text_night.setText("晚上："+wea.getText_night());
            low.setText("最低温度："+wea.getLow());
            high.setText("最高温度"+wea.getHigh());

            return view;
        }
    }

}

