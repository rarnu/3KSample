package com.isyscore.wh.commo.response

data class AjaxResult <T>(val code: Int, val msg: String, val data: T?) {
    companion object {
        fun success(): AjaxResult<*> = AjaxResult(200, "操作成功", null)
        fun success(msg: String): AjaxResult<*> = AjaxResult(200, msg, null)
        fun<T> success(obj: T?): AjaxResult<T> = AjaxResult(200, "操作成功", obj)
        fun<T> success(msg: String, obj: T?): AjaxResult<T> = AjaxResult(200, msg, obj)
        fun error(): AjaxResult<*> = AjaxResult(500, "操作失败", null)
        fun error(msg: String): AjaxResult<*> = AjaxResult(500, msg, null)
        fun<T> error(obj: T?): AjaxResult<T> = AjaxResult(500, "操作失败", obj)
        fun<T> error(msg: String, obj: T?): AjaxResult<T> = AjaxResult(500, msg, obj)
    }
}
