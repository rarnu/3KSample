#ifndef JNI_LIBRARY_H
#define JNI_LIBRARY_H

#include <jni.h>

extern "C" {

JNIEXPORT jstring JNICALL Java_com_isyscore_wh_native_Demo_sayHello(JNIEnv* env, jobject obj, jstring name);

};


#endif //JNI_LIBRARY_H
