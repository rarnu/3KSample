@file:Suppress("UNCHECKED_CAST")

package com.isyscore.wh.native

import javax.script.ScriptContext
import javax.script.ScriptEngineManager
import javax.script.SimpleScriptContext
import kotlin.script.experimental.jvmhost.jsr223.KotlinJsr223ScriptEngineImpl

object Scripting {

    private lateinit var engine: KotlinJsr223ScriptEngineImpl

    fun initialize() {
        engine = ScriptEngineManager().getEngineByExtension("kts") as KotlinJsr223ScriptEngineImpl
    }

    fun <T> runScript(code: String, params: Map<String, Any?>? = null): Pair<T?, Exception?> {
        val bindings = engine.createBindings().apply {
            for ((key, value) in (params ?: mapOf())) {
                put(key, value)
            }
        }
        val ctx = SimpleScriptContext().apply {
            setBindings(bindings, ScriptContext.ENGINE_SCOPE)
        }
        return try {
            engine.eval(code, ctx) as? T to null
        } catch (e: Exception) {
            null to e
        }
    }
}

inline fun <reified T> Any.getValue(field: String): T? =
    this::class.java.getDeclaredField(field).apply { isAccessible = true }.get(this) as? T