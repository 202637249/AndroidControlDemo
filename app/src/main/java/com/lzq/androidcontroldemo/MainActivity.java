package com.lzq.androidcontroldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzq.androidcontroldemo.activity.MyViewPageActivity;
import com.lzq.androidcontroldemo.activity.NDKActivity;
import com.lzq.androidcontroldemo.activity.ToastActivity;
import com.lzq.androidcontroldemo.activity.WebViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button toastButton;
    private Button viewPageButton;
    private Button ndkButton;
    private Button webViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();

    }

    public void initView(){

        toastButton = (Button)findViewById(R.id.activity_toasts);
        toastButton.setOnClickListener(MainActivity.this);
        viewPageButton =(Button)findViewById(R.id.activity_view_page);
        viewPageButton.setOnClickListener(MainActivity.this);
        ndkButton = (Button)findViewById(R.id.activity_ndk);
        ndkButton.setOnClickListener(MainActivity.this);
        webViewButton = (Button)findViewById(R.id.activity_webView);
        webViewButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_toasts:
                startActivity(new Intent(MainActivity.this, ToastActivity.class));
                break;
            case R.id.activity_view_page:
                startActivity(new Intent(MainActivity.this, MyViewPageActivity.class));
                break;
            case R.id.activity_ndk:
                startActivity(new Intent(MainActivity.this, NDKActivity.class));
                break;
            case R.id.activity_webView:
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                break;

            default:break;
        }
    }
}
