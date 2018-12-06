package com.androidchekhov.pagingrecyclerview.domain

sealed class CommentsEvent

object OnFirstPageLoading: CommentsEvent()

object OnFirstPageResults: CommentsEvent()

object OnNextPageLoading: CommentsEvent()

object OnNextPageResults: CommentsEvent()