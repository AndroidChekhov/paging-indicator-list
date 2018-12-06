package com.androidchekhov.pagingrecyclerview.arch

interface Store<Event, State> {
    fun send(event: Event)
    fun observe(observer: StateObserver<State>)
}