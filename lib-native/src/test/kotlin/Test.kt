package com.isyscore.wh.native

import kotlin.test.Test

class Test {

    private val CODE = """
        |data class SystemInfo(val osName: String, val osArch: String)
        |val si = SystemInfo(System.getProperty("os.name"), System.getProperty("os.arch"))
        |println("inScript = ${"$"}{si}")
        |si // return it
    """.trimMargin()

    private val CODE2 = """
        |println(a)
        |println(b)
    """.trimMargin()

    @Test
    fun testScript() {
        Scripting.initialize()
        val (data, _) = Scripting.runScript<Any>(CODE)
        println("inKotlin = $data")
        println("osName = ${data?.getValue<String>("osName")}")
        println("osArch = ${data?.getValue<String>("osArch")}")
    }

    @Test
    fun testSaveCompileResult() {
        Scripting.initialize()
        Scripting.runScript<Any>(CODE2, mapOf("a" to "aaaa", "b" to "bbbb"))
        Scripting.runScript<Any>(CODE2, mapOf("a" to "xxxx", "b" to "yyyy"))
    }
}


