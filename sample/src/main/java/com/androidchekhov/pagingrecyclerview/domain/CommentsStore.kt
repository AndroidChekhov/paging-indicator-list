package com.androidchekhov.pagingrecyclerview.domain

import com.androidchekhov.pagingrecyclerview.arch.StateObserver
import com.androidchekhov.pagingrecyclerview.arch.Store
import javax.inject.Inject

class CommentsStore @Inject constructor(
    initialState: CommentsState
): Store<CommentsEvent, CommentsState> {

    private var state : CommentsState = initialState

    private val observers = mutableListOf<StateObserver<CommentsState>>()

    override fun observe(observer: StateObserver<CommentsState>) {
        observer.stateChanged(null, state)
        observers.add(observer)
    }

    override fun send(event: CommentsEvent) {
        val newState = reduce(event)
        updateState(newState)
    }

    private fun reduce(event: CommentsEvent) : CommentsState {
        return when(event) {
            is OnFirstPageLoading -> LoadingFirstPage

            is OnNextPageLoading -> Paging

            is OnFirstPageResults -> Results

            is OnNextPageResults -> Results
        }
    }

    private fun updateState(newState: CommentsState) {
        observers.forEach {
            it.stateChanged(state, newState)
        }.also {
            state = newState
        }
    }
}

