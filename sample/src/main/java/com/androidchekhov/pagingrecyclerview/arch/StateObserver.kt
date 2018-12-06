package com.androidchekhov.pagingrecyclerview.arch

interface StateObserver<State> {
    fun stateChanged(oldState: State?, newState: State)
}