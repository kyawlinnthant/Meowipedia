import com.everest.categories.data.model.search.BreedDTO
import kotlinx.serialization.Serializable

class SearchResponse : ArrayList<SearchResponseItem>()

@Serializable
data class SearchResponseItem(
    val breedDTOS: List<BreedDTO>? = listOf(),
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)
