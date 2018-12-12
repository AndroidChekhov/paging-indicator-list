package com.androidchekhov.pagingrecyclerview.domain

sealed class CommentsState

object Starting: CommentsState()

object LoadingFirstPage : CommentsState()

object Paging : CommentsState()

object Results : CommentsState()

