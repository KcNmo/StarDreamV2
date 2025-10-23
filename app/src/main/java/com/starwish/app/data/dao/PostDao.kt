package com.starwish.app.data.dao

import androidx.room.*
import com.starwish.app.data.model.Post
import com.starwish.app.data.model.PostComment
import com.starwish.app.data.model.PostLike
import com.starwish.app.data.model.PostVisibility
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts WHERE userId = :userId ORDER BY createdAt DESC")
    fun getPostsByUserId(userId: String): Flow<List<Post>>

    @Query("SELECT * FROM posts WHERE visibility = :visibility ORDER BY createdAt DESC")
    fun getPostsByVisibility(visibility: PostVisibility): Flow<List<Post>>

    @Query("SELECT * FROM posts WHERE userId IN (:userIds) ORDER BY createdAt DESC")
    fun getPostsByUserIds(userIds: List<String>): Flow<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :postId")
    suspend fun getPostById(postId: String): Post?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)

    @Query("SELECT * FROM post_likes WHERE postId = :postId AND userId = :userId")
    suspend fun getPostLike(postId: String, userId: String): PostLike?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostLike(like: PostLike)

    @Delete
    suspend fun deletePostLike(like: PostLike)

    @Query("SELECT * FROM post_likes WHERE postId = :postId")
    fun getPostLikes(postId: String): Flow<List<PostLike>>

    @Query("SELECT * FROM post_comments WHERE postId = :postId ORDER BY createdAt ASC")
    fun getPostComments(postId: String): Flow<List<PostComment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostComment(comment: PostComment)

    @Delete
    suspend fun deletePostComment(comment: PostComment)
}