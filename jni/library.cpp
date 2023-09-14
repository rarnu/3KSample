#include "library.h"

#include <iostream>

using namespace std;

JNIEXPORT jstring JNICALL Java_com_isyscore_wh_native_Demo_sayHello(JNIEnv* env, jobject obj, jstring name) {
    auto str = string (env->GetStringUTFChars(name, nullptr));
    str = "Hello " + str + "!";
    return env->NewStringUTF(str.c_str());
}
