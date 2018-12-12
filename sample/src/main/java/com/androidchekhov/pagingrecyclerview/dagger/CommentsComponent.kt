package com.androidchekhov.pagingrecyclerview.dagger

import com.androidchekhov.pagingrecyclerview.presentation.CommentsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CommentsModule::class])
interface CommentsComponent {
    fun inject(commentsActivity: CommentsActivity)
}