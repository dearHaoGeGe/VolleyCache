package com.my.volleycachedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.NetworkImageView;
import com.my.volleycachedemo.volley.VolleySingleton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private NetworkImageView net_iv;
    private String url = "http://img.jrjimg.cn/2010/10/20101015125850689.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        net_iv = (NetworkImageView) findViewById(R.id.net_iv);

        btn.setOnClickListener(this);
        net_iv.setBackgroundResource(R.mipmap.aa);  //可以通过此方法设置本地的图片
        //MyRequestQueue.setNetImage(net_iv,url);
        //VolleySingleton.setNetImage(net_iv,url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                VolleySingleton.setNetImage(net_iv, url);
                break;
        }
    }
}
