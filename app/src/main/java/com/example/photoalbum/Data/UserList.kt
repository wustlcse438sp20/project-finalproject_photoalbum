package com.example.photoalbum.Data

data class User (
    val email: String? = null,
    val username: String? = null,
    val userID: String? = null,
    val albumLink: String? = null
)


data class Album (
    val owner: String? = null,
    val albumName: String? = null,
    val albumLink: String? = null,
    val albumDescription: String? = null,
    val isPublic: Boolean? = null,
    //Stores links to Firebase Storage for each picture
    val pictures: List<String> = emptyList(),
    //Stores userids or usernames that can view album
    val allowedUserList: List<String> = emptyList(),
    //Stores arraylist of userids or usernames that are mods
    val isModList: List<String> = emptyList(),
    val comments: List<Comments> = emptyList()

)






data class Comments (
    val commentBody: String? = null,
    val commentAuthor: String? = null
)