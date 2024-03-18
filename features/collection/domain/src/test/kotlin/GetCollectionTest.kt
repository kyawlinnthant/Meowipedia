import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.data.repository.CollectionRepo
import com.everest.domain.GetCollection
import com.everest.domain.model.CollectionVO
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class GetCollectionTest {
    private var collectionRepo: CollectionRepo? = null

    @BeforeEach
    fun setUp() {
        collectionRepo = mockk(relaxed = true)
    }

    @AfterEach
    fun tearDown() {
        collectionRepo = null
    }

    @Test
    @DisplayName("Get Collection Success")
    fun uploadFileSuccess() = runTest {
        val response = listOf(
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

        coEvery { collectionRepo?.getCollection() } returns DataResult.Success(response)

        collectionRepo?.let {
            val getCollection = GetCollection(it)
            val actual = getCollection.invoke()
            assertThat(actual).isEqualTo(
                DataResult.Success(
                    listOf(
                        CollectionVO(
                            id = 123,
                            subId = "23424",
                            url = "www.google.com"
                        ),
                        CollectionVO(
                            id = 121,
                            subId = "23424",
                            url = "www.google.com"
                        )
                    )
                )
            )
        }
    }

    @Test
    @DisplayName("Collection Failed")
    fun getCollectFail() = runTest {
        coEvery { collectionRepo?.getCollection() } returns DataResult.Failed(NetworkError.SomethingWrong)
        collectionRepo?.let {
            val getCollection = GetCollection(it)
            val actual = getCollection.invoke()
            assertThat(actual).isEqualTo(DataResult.Failed(NetworkError.SomethingWrong))
        }

    }
}
