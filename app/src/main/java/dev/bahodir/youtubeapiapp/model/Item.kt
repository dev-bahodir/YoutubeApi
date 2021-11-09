package dev.bahodir.youtubeapiapp.model

data class Item(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)