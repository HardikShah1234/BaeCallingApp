package com.harry.baecallingapp.data

/**
 * Holds the result as success or failure.
 */
sealed class Resource <out R>{
    /**
     * If the request is success then give result as output.
     */
    data class Success<out R>(val result: R): Resource<R>()

    /**
     * If the request is failed, throws exception.
     */
    data class Failure(val exception: Exception): Resource<Nothing>()

    /**
     * If in loading state then show the progress bar.
     */
    object Loading: Resource<Nothing>()
}
