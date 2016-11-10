package com.lzq.androidcontroldemo.utils;

/**
 * Created by Dell on 2016/10/20 0020.
 */
public class NdkUtil {

    static{
            System.loadLibrary("com_lzq_androidcontroldemo_utils_NdkUtil");
    }

    public native String getMD5();
}
