//
// Created by Dell on 2016/10/20 0020.
//

#include "com_lzq_androidcontroldemo_utils_NdkUtil.h"

JNIEXPORT jstring JNICALL Java_com_lzq_androidcontroldemo_utils_NdkUtil_getMD5
  (JNIEnv *env, jobject obj){
   return  (*env)->NewStringUTF(env, "Hello from JNI !");
  };