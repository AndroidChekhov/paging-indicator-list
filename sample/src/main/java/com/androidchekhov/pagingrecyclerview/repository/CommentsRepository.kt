package com.androidchekhov.pagingrecyclerview.repository

import com.androidchekhov.pagingrecyclerview.domain.Comment

interface CommentsRepository {
    suspend fun getComments(page: Int) : List<Comment>
}