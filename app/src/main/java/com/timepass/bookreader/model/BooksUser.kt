package com.timepass.bookreader.model

data class BooksUser(
    val userEmail: String = "",
    val userId: String = "",
    val id: String? = "",
    val displayName: String = "",
    val avatarUrl: String = ""
) {
    fun toMap(): MutableMap<String,Any>{
        return mutableMapOf(
            "user_id" to this.userId,
        "user_name" to this.displayName,
        "avatar_url" to this.avatarUrl,
            "user_email" to this.userEmail
        )
    }
}
