package com.androidchekhov.pagingrecyclerview.repository

interface CommentsRepository {
    suspend fun getComments(page: Int) : List<Comment>
}