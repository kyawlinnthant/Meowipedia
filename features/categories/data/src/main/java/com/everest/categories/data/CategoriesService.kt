import com.everest.categories.data.model.categories.CategoryDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesService {
    companion object {
        const val CATEGORY = "breeds"
        private const val SEARCH_CATEGORY = "breeds/search"
    }

    @GET(CATEGORY)
    suspend fun categories(
        @Query("limit") limit:Int = 10,
        @Query("page") page:Int = 0,
    ): Response<List<CategoryDTO>>

    @GET(SEARCH_CATEGORY)
    suspend fun searchCategories(): Response<List<CategoryDTO>>
}