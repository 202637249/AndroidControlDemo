package com.lzq.androidcontroldemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzq.androidcontroldemo.R;
import com.lzq.androidcontroldemo.utils.NdkUtil;
import com.lzq.androidcontroldemo.utils.Toasts;

public class NDKActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_hello;
    private Button bt_md5;
    private NdkUtil ndkUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);

        initView();
    }
    String s ="";
    public void initView(){
        ndkUtil = new NdkUtil();
        s = ndkUtil.getMD5();
        bt_hello = (Button)findViewById(R.id.bt_hello);
        bt_md5 = (Button)findViewById(R.id.bt_MD5);
        bt_hello.setOnClickListener(this);
        bt_md5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_hello:
                Toasts.toastDefault(NDKActivity.this, s);
                break;
            case R.id.bt_MD5:Toasts.toastDefaultPosition(NDKActivity.this, "自定义位置");break;
            default:break;

        }
    }
}
