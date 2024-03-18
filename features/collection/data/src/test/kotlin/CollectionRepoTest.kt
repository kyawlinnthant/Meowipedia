import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.data.repository.CollectionRepo
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CollectionRepoTest {
    private val collectionRepo: CollectionRepo = mockk(relaxed = true)


    @Test
    @DisplayName("Get Collection Success")
    fun uploadFileSuccess() = runTest {
        val expected = listOf(
            CollectionDTO(
                subId = "23424",
                id = 123,
                createdAt = "2023-10-28T17:39:28.000Z",
                image = Image(
                    id = "1n7",
                    url = "www.google.com"
                ),
                imageID = "asf2",
                userId = "1ejqec",

                ),
            CollectionDTO(
                subId = "23424",
                id = 121,
                createdAt = "2023-10-28T17:39:28.000Z",
                image = Image(
                    id = "1n7",
                    url = "www.google.com"
                ),
                imageID = "asf2",
                userId = "1ejqec",
            )
        )
        coEvery { collectionRepo.getCollection() } returns DataResult.Success(data = expected)
        val response = collectionRepo.getCollection()
        assertThat(response).isEqualTo(DataResult.Success(data = expected))
    }

    @Test
    @DisplayName("Collection Failed")
    fun getCollectionFail() = runTest {
        val errorResponse = NetworkError.SomethingWrong
        coEvery { collectionRepo.getCollection() } returns DataResult.Failed(error = errorResponse)

        val response = collectionRepo.getCollection()

        assertThat(response).isEqualTo(DataResult.Failed(error = errorResponse))
    }
}
