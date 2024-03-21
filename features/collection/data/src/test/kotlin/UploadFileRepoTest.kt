import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.data.UploadFileDTO
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

class UploadFileRepoTest {
    private val uploadFileRepo: UploadFileRepo = mockk(relaxed = true)


    @Test
    @DisplayName("Test Upload File Success")
    fun uploadFileSuccess() = runTest {
        val expected = UploadFileDTO(
            approved = 1,
            pending = 0,
            url = "www.google.com",
            id = "werwrwr",
            height = 150,
            width = 150,
            originalFileName = "cat.jpg",
            subId = "testing-user1"
        )
        val file: File = mockk(relaxed = true)
        coEvery { uploadFileRepo.uploadFile(file) } returns DataResult.Success(data = expected)
        val response = uploadFileRepo.uploadFile(file)
        assertThat(response).isEqualTo(DataResult.Success(data = expected))
    }

    @Test
    @DisplayName("Test Upload File Failed")
    fun uploadFileFail() = runTest {
        val errorResponse = NetworkError.SomethingWrong
        val file: File = mockk(relaxed = true)
        coEvery { uploadFileRepo.uploadFile(file) } returns DataResult.Failed(error = errorResponse)

        val response = uploadFileRepo.uploadFile(file)

        assertThat(response).isEqualTo(DataResult.Failed(error = errorResponse))
    }
}
