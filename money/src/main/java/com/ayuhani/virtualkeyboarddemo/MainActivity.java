package com.ayuhani.virtualkeyboarddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ayuhani.virtualkeyboarddemo.alipay.AliPayActivity;
import com.ayuhani.virtualkeyboarddemo.normal.NormalActivity;
import com.ayuhani.virtualkeyboarddemo.wechat.WeChatPayActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onViewClicked(View view) {
        startActivity(new Intent(this, WeChatPayActivity.class));
    }
}
