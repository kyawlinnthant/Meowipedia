import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDTO(
    @SerialName("created_at")
    val createdAt: String? = null,
    val height: Int? = null,
    val id: String? = null,
    @SerialName("original_filename")
    val originalFilename: String,
    @SerialName("sub_id")
    val subId: String? = null,
    val url: String? = null,
    val width: Int? = null
)
