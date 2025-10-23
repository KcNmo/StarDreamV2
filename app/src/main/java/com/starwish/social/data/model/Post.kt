package com.starwish.social.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: String = "",
    val authorId: String = "",
    val content: String = "",
    val images: List<String> = emptyList(),
    val type: PostType = PostType.PUBLIC,
    val visibility: PostVisibility = PostVisibility.PUBLIC,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val isLiked: Boolean = false,
    val isAnonymous: Boolean = false,
    val tags: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable

enum class PostType {
    TEXT, IMAGE, VIDEO, ANONYMOUS_QUESTION, ANONYMOUS_HELP
}

enum class PostVisibility {
    PUBLIC, FRIENDS, CUSTOM
}

@Parcelize
data class Comment(
    val id: String = "",
    val postId: String = "",
    val authorId: String = "",
    val content: String = "",
    val parentId: String? = null,
    val likes: Int = 0,
    val isLiked: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable