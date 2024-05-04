package com.b1nd.mentomen.domain.util

import android.util.Log

class Log {
    companion object {
        private const val IS_DEBUG_MODE: Boolean = false
        private const val PRE_TAG: String = "MenToMen_"

        fun e(
            tag: String,
            msg: String,
        ) {
            if (IS_DEBUG_MODE) {
                Log.e(PRE_TAG + tag, msg)
            }
        }

        fun e(
            tag: String,
            e: Error,
        ) {
            if (IS_DEBUG_MODE) {
                val errMsg = getErrorlog(e)
                Log.e(PRE_TAG + tag, errMsg)
            }
        }

        fun e(
            tag: String,
            e: Exception,
        ) {
            if (IS_DEBUG_MODE) {
                val errMsg = getErrorlog(e)
                Log.e(PRE_TAG + tag, errMsg)
            }
        }

        fun i(
            tag: String,
            msg: String,
        ) {
            if (IS_DEBUG_MODE) {
                Log.i(PRE_TAG + tag, msg)
            }
        }

        fun v(
            tag: String,
            msg: String,
        ) {
            if (IS_DEBUG_MODE) {
                Log.v(PRE_TAG + tag, msg)
            }
        }

        fun d(
            tag: String,
            msg: String,
        ) {
            if (IS_DEBUG_MODE) {
                Log.d(PRE_TAG + tag, msg)
            }
        }

        fun getErrorlog(e: Exception): String {
            val sb: StringBuffer = StringBuffer("")
            try {
                sb.append(e.toString())
                sb.append("\n")

                val element: Array<StackTraceElement> = e.stackTrace

                for (value in element) {
                    sb.append("\tat")
                    sb.append(value.toString())
                    sb.append("\n")
                }
            } catch (ex: Exception) {
                return e.toString()
            }

            return sb.toString()
        }

        fun getErrorlog(e: Error): String {
            val sb: StringBuffer = StringBuffer("")
            try {
                sb.append(e.toString())
                sb.append("\n")

                val element: Array<StackTraceElement> = e.stackTrace

                for (value in element) {
                    sb.append("\tat")
                    sb.append(value.toString())
                    sb.append("\n")
                }
            } catch (_ex: Exception) {
                return e.toString()
            }

            return sb.toString()
        }
    }
}
