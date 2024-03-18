import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.data.UploadFileDTO
import com.everest.data.repository.UploadFileRepo
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
    private var uploadFileRepo: UploadFileRepo? = null
    private val file: File = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        uploadFileRepo = mockk(relaxed = true)
    }

    @AfterEach
    fun tearDown() {
        uploadFileRepo = null
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

        coEvery { uploadFileRepo?.uploadFile(file) } returns DataResult.Success(response)

        uploadFileRepo?.let {
            val uploadFile = UploadFile(it)
            val actual = uploadFile.invoke(file)
            assertThat(actual).isEqualTo(DataResult.Success(true))
        }
    }

    @Test
    @DisplayName("Upload File Failed")
    fun uploadFileFailed() = runTest {
        coEvery { uploadFileRepo?.uploadFile(file) } returns DataResult.Failed(NetworkError.SomethingWrong)
        uploadFileRepo?.let {
            val uploadFile = UploadFile(it)
            val actual = uploadFile.invoke(file)
            assertThat(actual).isEqualTo(DataResult.Failed(NetworkError.SomethingWrong))
        }

    }
}
