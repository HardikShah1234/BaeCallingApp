package com.harry.baecallingapp.utils.firebase

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cancellableContinuation ->
        addOnCompleteListener { task ->
            if (task.exception != null) {
                cancellableContinuation.resumeWithException(task.exception!!)
            } else {
                cancellableContinuation.resume(task.result, null)
            }
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.showMsg(
    msg: String,
    duration: Int = Toast.LENGTH_SHORT
) = Toast.makeText(this, msg, duration).show()