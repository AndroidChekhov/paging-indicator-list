package com.androidchekhov.pagingrecyclerview.dagger

import com.androidchekhov.pagingrecyclerview.domain.CommentsState
import com.androidchekhov.pagingrecyclerview.domain.CommentsStore
import com.androidchekhov.pagingrecyclerview.domain.Starting
import com.androidchekhov.pagingrecyclerview.presentation.ViewModelFactory
import com.androidchekhov.pagingrecyclerview.repository.CommentsRepository
import com.androidchekhov.pagingrecyclerview.repository.MockCommentsRepository
import com.androidchekhov.pagingrecyclerview.repository.PagedListCommentsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommentsModule {

    @Provides
    fun providesInitialState(): CommentsState = Starting

    @Provides
    @Singleton
    fun providesCommentsStore(
        initialState: CommentsState
    ): CommentsStore = CommentsStore(initialState)

    @Provides
    @Singleton
    fun providesCommentsRepository(): CommentsRepository = MockCommentsRepository()

    @Provides
    @Singleton
    fun providesPagedListCommentsRepository(
        store: CommentsStore,
        commentsRepository: CommentsRepository
    ) : PagedListCommentsRepository = PagedListCommentsRepository(store, commentsRepository)

    @Provides
    @Singleton
    fun providesViewModelFactory (
        store: CommentsStore,
        pagedListCommentsRepository: PagedListCommentsRepository
    ) : ViewModelFactory = ViewModelFactory(store, pagedListCommentsRepository)
}