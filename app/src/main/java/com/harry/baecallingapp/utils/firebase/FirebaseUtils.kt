package com.harry.baecallingapp.utils.firebase

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

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