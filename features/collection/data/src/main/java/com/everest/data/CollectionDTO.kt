import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDTO(
    @SerialName("created_at")
    val createdAt: String,
    val id: Int,
    val image: Image,
    @SerialName("image_id")
    val imageID: String,
    @SerialName("sub_id")
    val subId: String,
    @SerialName("user_id")
    val userId: String
)

@Serializable
data class Image(
    val id: String? = null,
    val url: String? = null
)
