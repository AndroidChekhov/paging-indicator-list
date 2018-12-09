package com.androidchekhov.pagingrecyclerview.dagger

import androidx.lifecycle.LifecycleOwner
import com.androidchekhov.pagingrecyclerview.presentation.CommentsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CommentsModule::class])
interface CommentsComponent {
    fun inject(commentsActivity: CommentsActivity)
}