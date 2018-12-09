package com.androidchekhov.pagingrecyclerview

import android.app.Application
import com.androidchekhov.pagingrecyclerview.dagger.CommentsComponent
import com.androidchekhov.pagingrecyclerview.dagger.DaggerCommentsComponent

class CommentsApplication: Application() {

    lateinit var commentsComponent: CommentsComponent

    override fun onCreate() {
        super.onCreate()

        commentsComponent = DaggerCommentsComponent.builder().build()
    }
}