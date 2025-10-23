package com.starwish.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "posts")
@Parcelize
data class Post(
    @PrimaryKey
    val id: String,
    val userId: String,
    val content: String,
    val images: List<String> = emptyList(),
    val type: PostType,
    val visibility: PostVisibility,
    val isAnonymous: Boolean = false,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
enum class PostType : Parcelable {
    MOMENT,
    ANONYMOUS_QUESTION,
    HELP_REQUEST
}

@Parcelize
enum class PostVisibility : Parcelable {
    FRIENDS_ONLY,
    PUBLIC,
    PRIVATE
}

@Entity(tableName = "post_likes")
@Parcelize
data class PostLike(
    @PrimaryKey
    val id: String,
    val postId: String,
    val userId: String,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Entity(tableName = "post_comments")
@Parcelize
data class PostComment(
    @PrimaryKey
    val id: String,
    val postId: String,
    val userId: String,
    val content: String,
    val parentCommentId: String? = null,
    val isAnonymous: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable