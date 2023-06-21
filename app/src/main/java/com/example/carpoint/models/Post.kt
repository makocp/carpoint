package com.example.carpoint.models

import java.util.UUID

/**
 * Class representing a post.
 *
 * @property id The unique identifier of the post.
 * @property title The title of the post.
 * @property content The content of the post.
 * @property author The author of the post.
 */
class Post (
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val author: User
)
