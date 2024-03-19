import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDTO(
    @SerialName("created_at")
    val createdAt: String,
    val height: Int,
    val id: String,
    @SerialName("original_filename")
    val originalFilename: String,
    @SerialName("sub_id")
    val subId: String? = null,
    val url: String,
    val width: Int
)
