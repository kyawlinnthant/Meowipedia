import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.data.UploadFileDTO
import com.everest.data.repository.CollectionRepo
import com.everest.domain.UploadFile
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File


class UploadFileTest {
    private var collectionRepo: CollectionRepo? = null
    private val file: File = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        collectionRepo = mockk(relaxed = true)
    }

    @AfterEach
    fun tearDown() {
        collectionRepo = null
    }

    @Test
    @DisplayName("Upload File Success")
    fun uploadFileSuccess() = runTest {
        val response = UploadFileDTO(
            approved = 1,
            pending = 0,
            url = "www.google.com",
            id = "werwrwr",
            height = 150,
            width = 150,
            originalFileName = "cat.jpg",
            subId = "testing-user1"
        )

        coEvery { collectionRepo?.uploadFile(file) } returns DataResult.Success(response)

        collectionRepo?.let {
            val uploadFile = UploadFile(it)
            val actual = uploadFile.invoke(file)
            assertThat(actual).isEqualTo(DataResult.Success(true))
        }
    }

    @Test
    @DisplayName("Upload File Failed")
    fun uploadFileFailed() = runTest {
        coEvery { collectionRepo?.uploadFile(file) } returns DataResult.Failed(NetworkError.SomethingWrong)
        collectionRepo?.let {
            val uploadFile = UploadFile(it)
            val actual = uploadFile.invoke(file)
            assertThat(actual).isEqualTo(DataResult.Failed(NetworkError.SomethingWrong))
        }

    }
}
