package com.isyscore.wh.native

object Demo {

    init {
        try {
            System.loadLibrary("demo")
        } catch (th: Throwable) {
            println("load library error: $th")
        }
    }

    external fun sayHello(name: String): String

}