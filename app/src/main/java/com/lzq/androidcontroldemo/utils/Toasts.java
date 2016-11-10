package com.lzq.androidcontroldemo.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzq.androidcontroldemo.R;

/**
 * Created by Dell on 2016/10/19 0019.
 */
public class Toasts {
    private static View view;
    //默认样式
    public static void toastDefault(Context context,String content){
        Toast toast=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        toast.show();
    }
    //默认样式,定义位置
    public static void toastDefaultPosition(Context context,String content){
        Toast toast=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    //默认带图片的
    public static void toastImage(Context context,String content){
        Toast toast=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        ImageView image = new ImageView(context);
        image.setImageResource(R.mipmap.ic_launcher);
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.addView(image,0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    //自定义Toast
    public static void toast(Context context,String content)
    {
        view= LayoutInflater.from(context).inflate(R.layout.toast_item, null);
        TextView textView=(TextView) view.findViewById(R.id.toast_text);
        textView.setText(content);
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public static void toast(Context context,int content)
    {
        view=LayoutInflater.from(context).inflate(R.layout.toast_item, null);
        TextView textView=(TextView) view.findViewById(R.id.toast_text);
        textView.setText(content);
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_HORIZONTAL);
        toast.setView(view);
        toast.show();
    }

    public static void toast(Context context,int id,String content)
    {
        view=LayoutInflater.from(context).inflate(R.layout.toast_item2, null);
        TextView textView=(TextView) view.findViewById(R.id.toast_text);
        LinearLayout imageView=(LinearLayout) view.findViewById(R.id.toast_img);
        imageView.setBackgroundResource(id);
        textView.setText(content);
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

}
