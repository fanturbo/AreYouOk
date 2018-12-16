package com.ayuhani.virtualkeyboarddemo.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ayuhani.virtualkeyboarddemo.OnPasswordFinishedListener;
import com.ayuhani.virtualkeyboarddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeChatPayActivity extends AppCompatActivity implements OnPasswordFinishedListener {

    LinearLayout llParent;
    private WeChatPayWindow weChatPayWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_pay);
        ButterKnife.bind(this);
        llParent = (LinearLayout) findViewById(R.id.ll_parent);
        findViewById(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weChatPayWindow = new WeChatPayWindow(WeChatPayActivity.this);
                weChatPayWindow.setOnPasswordFinishedListener(WeChatPayActivity.this);
                weChatPayWindow.show(llParent);
            }
        });
    }

    @Override
    public void onFinish(String password) {
        //        Toast.makeText(this, "你输入的密码是：" + password, Toast.LENGTH_SHORT).show();
        //        weChatPayWindow.dismiss();
        startActivity(new Intent(this, SuccessActivity.class));

    }
}
