import model.search.Breed

class SearchResponse : ArrayList<SearchResponseItem>()

data class SearchResponseItem(
    val breeds: List<Breed>? = listOf(),
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)
