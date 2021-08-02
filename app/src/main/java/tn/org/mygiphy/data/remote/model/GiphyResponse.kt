package tn.org.mygiphy.data.remote.model


import com.google.gson.annotations.SerializedName

data class GiphyModel(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("meta")
    val meta: Meta
)

data class Data(
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content_url")
    val contentUrl: String,
    @SerializedName("source_tld")
    val sourceTld: String,
    @SerializedName("source_post_url")
    val sourcePostUrl: String,
    @SerializedName("is_sticker")
    val isSticker: Int,
    @SerializedName("images")
    val images: Images,
)

data class Images(
    @SerializedName("downsized")
    val downsized: DownSized,
)

data class DownSized(
    val url: String,
)

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int
)

data class Meta(
    @SerializedName("status")
    val status: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("response_id")
    val responseId: String
)
