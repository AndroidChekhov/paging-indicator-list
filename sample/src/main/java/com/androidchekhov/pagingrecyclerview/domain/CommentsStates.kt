package com.androidchekhov.pagingrecyclerview.domain

sealed class CommentsState

/**
 * The view kicks off requests.
 */
object Starting: CommentsState()

/**
 * The view displays a progress bar.
 */
object LoadingFirstPage : CommentsState()

/**
 * The view displays a paging progress bar.
 */
object Paging : CommentsState()

/**
 * The view hides any progress bars.
 */
object Results : CommentsState()

