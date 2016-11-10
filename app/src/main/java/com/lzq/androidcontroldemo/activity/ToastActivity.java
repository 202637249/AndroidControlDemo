package com.lzq.androidcontroldemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzq.androidcontroldemo.R;
import com.lzq.androidcontroldemo.utils.Toasts;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener{

    private Button defaultButton;
    private Button positionButton;
    private Button imageButton;
    private Button inputSButton;
    private Button inturIButton;
    private Button backgroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        initView();
    }
    public void initView(){
        defaultButton = (Button)findViewById(R.id.default_toast);
        defaultButton.setOnClickListener(this);
        positionButton = (Button)findViewById(R.id.position_toast);
        positionButton.setOnClickListener(this);
        imageButton = (Button)findViewById(R.id.image_toast);
        imageButton.setOnClickListener(this);
        inputSButton = (Button)findViewById(R.id.input_sting_toast);
        inputSButton.setOnClickListener(this);
        inturIButton = (Button)findViewById(R.id.input_id_toast);
        inturIButton.setOnClickListener(this);
        backgroupButton = (Button)findViewById(R.id.backgroup_toast);
        backgroupButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.default_toast: Toasts.toastDefault(ToastActivity.this, "默认Toast");break;
            case R.id.position_toast:Toasts.toastDefaultPosition(ToastActivity.this, "自定义位置");break;
            case R.id.image_toast:Toasts.toastImage(ToastActivity.this, "默认带图片");break;
            case R.id.input_sting_toast:Toasts.toast(ToastActivity.this, "自定义文字");break;
            case R.id.input_id_toast:Toasts.toast(ToastActivity.this, R.string.my_toast);break;
            case R.id.backgroup_toast:Toasts.toast(ToastActivity.this, R.mipmap.ic_launcher, "自定义背景");break;
            default:break;

        }
    }
}
