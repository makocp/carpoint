package com.example.carpoint.models

import java.util.UUID

class Post (
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val author: User
    )