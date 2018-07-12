package com.example.myreditt.adapter

import com.example.myreditt.models.domain.Preview
import com.example.myreditt.models.domain.RedditContentData
import com.example.myreditt.models.domain.RedditResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException

import java.lang.reflect.Type

/**
 * @author Sanoop.
 */

class RedditContentDataParser : JsonDeserializer<RedditContentData> {

    private val gson: Gson = GsonBuilder()
            .registerTypeAdapter(RedditContentData::class.java, this)
            .create()

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext): RedditContentData {
        val redditContentData = RedditContentData()
        val jsonObject = json.asJsonObject
        redditContentData.id = jsonObject.get("id").asString

        if (jsonObject.has(TITLE)) {
            redditContentData.title = jsonObject.get(TITLE).asString
        }
        if (jsonObject.has(SUBREDDITNAMEPREFIXED)) {
            redditContentData.subredditNamePrefixed = jsonObject.get(SUBREDDITNAMEPREFIXED).asString
        }
        if (jsonObject.has(OVER18)) {
            redditContentData.isNsfw = jsonObject.get(OVER18).asBoolean
        }
        if (jsonObject.has(CREATED_AT)) {
            redditContentData.createdAt = jsonObject.get(CREATED_AT).asLong
        }
        if (jsonObject.has(THUMBNAIL)) {
            redditContentData.thumbnail = jsonObject.get(THUMBNAIL).asString
        }
        if (jsonObject.has(AUTHOR)) {
            redditContentData.author = jsonObject.get(AUTHOR).asString
        }
        if (jsonObject.has(SUBREDDIT)) {
            redditContentData.subreddit = jsonObject.get(SUBREDDIT).asString
        }
        if (jsonObject.has(UPS)) {
            redditContentData.upVotes = jsonObject.get(UPS).asInt
        }
        if (jsonObject.has(SCORES)) {
            redditContentData.score = jsonObject.get(SCORES).asInt
        }
        if (jsonObject.has(NUMCOMMENTS)) {
            redditContentData.numComments = jsonObject.get(NUMCOMMENTS).asInt
        }
        if (jsonObject.has(BODY_HTML)) {
            redditContentData.body = jsonObject.get(BODY_HTML).asString
        }
        if (jsonObject.has(PERMALINK)) {
            redditContentData.permalink = jsonObject.get(PERMALINK).asString
        }
        if (jsonObject.has(PREVIEW)) {
            val previewJsonElement = jsonObject.get(PREVIEW)
            redditContentData.preview = gson.fromJson(previewJsonElement, Preview::class.java)
        }
        if (jsonObject.has(REPLIES)) {
            val redditResponseJsonElement = jsonObject.get(REPLIES)
            if (redditResponseJsonElement.isJsonObject) {
                redditContentData.replies = gson.fromJson(redditResponseJsonElement, RedditResponse::class.java)
            }
        }
        return redditContentData
    }

    companion object {

        private val ID = "id"
        private val TITLE = "title"
        private val BODY_HTML = "body"
        private val REPLIES = "replies"
        private val OVER18 = "over_18"
        private val AUTHOR = "author"
        private val THUMBNAIL = "thumbnail"
        private val NUMCOMMENTS = "num_comments"
        private val PREVIEW = "preview"
        private val PERMALINK = "permalink"
        private val SUBREDDITNAMEPREFIXED = "subreddit_name_prefixed"
        private val CREATED_AT = "created_utc"
        private val SUBREDDIT = "subreddit"
        private val UPS = "ups"
        private val SCORES = "score"
    }

}
