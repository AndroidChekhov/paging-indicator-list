package com.androidchekhov.pagingrecyclerview.arch

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, onItem: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer<T> {
        if (it != null) {
            onItem(it)
        }
    })
}