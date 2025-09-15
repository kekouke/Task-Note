package com.kekouke.tasknote.utils.models

import android.content.Context

sealed interface Text {
    class Resource(val resId: Int, vararg val args: Any) : Text {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Resource) return false
            if (resId != other.resId) return false

            return args.contentEquals(other.args)
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    class Value(val value: String) : Text {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Value) return false

            return value == other.value
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }
    }
}

fun Text.asString(context: Context): String {
    return when (this) {
        is Text.Resource -> context.getString(resId, *args)
        is Text.Value -> value
    }
}
