package com.everest.categories.data.repository

import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CategoriesRepoTest {
    private var repo: CategoriesRepo? = null

    @Before
    fun setup() {
        repo = mockk(relaxed = true)
    }

    @After
    fun teardown() {
        repo = null
    }

    @Test
    fun `service returns success makes repo returns DataResult-Success `() = runTest {
        val successResponse = listOf(
            CategoryDTO(),
            CategoryDTO()
        )
        coEvery { repo?.fetchCategories() } returns DataResult.Success(data = successResponse)

        repo?.let {
            val response = repo?.fetchCategories()
            Truth.assertThat(response).isEqualTo(DataResult.Success(data = successResponse))
        }
    }

    @Test
    fun `service returns failed makes repo returns DataResult-Failed `() = runTest {
        val errorResponse = NetworkError.SomethingWrong
        coEvery { repo?.fetchCategories() } returns DataResult.Failed(error = errorResponse)

        repo?.let {
            val response = repo?.fetchCategories()
            Truth.assertThat(response).isEqualTo(DataResult.Failed(error = errorResponse))
        }
    }
}