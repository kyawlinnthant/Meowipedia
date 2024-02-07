import com.everest.categories.data.model.categories.CategoryDTO
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesService {
    companion object {
        const val CATEGORY = "breeds"
        private const val SEARCH_CATEGORY = "breeds/search"
    }

    @GET(CATEGORY)
    suspend fun categories(): Response<List<CategoryDTO>>

    @GET(SEARCH_CATEGORY)
    suspend fun searchCategories(): Response<List<CategoryDTO>>
}