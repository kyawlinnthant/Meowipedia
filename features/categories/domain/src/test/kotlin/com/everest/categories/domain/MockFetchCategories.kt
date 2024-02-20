package com.everest.categories.domain

import com.everest.categories.data.model.categories.CategoryDTO
import com.everest.categories.data.model.search.CategoryImageDTO
import com.everest.categories.data.repository.CategoriesRepo
import com.everest.categories.domain.usecase.FetchCategories
import com.everest.categories.domain.vo.CategoryVO
import com.everest.util.result.DataResult
import com.everest.util.result.NetworkError
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class MockFetchCategories {
    private var categoriesRepo: CategoriesRepo? = null

    @Before
    fun setUp() {
        categoriesRepo = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        categoriesRepo = null
    }

    @Test
    fun `fetch categories`() = runTest {
        val response = mutableListOf(
            CategoryDTO(
                id = "1", name = "Testing", image = CategoryImageDTO(
                    url = "Testing URL"
                )
            ),
            CategoryDTO(
                id = "2", name = "Testing", image = CategoryImageDTO(
                    url = "Testing URL"
                )
            ),
        )

        val result = mutableListOf(
            CategoryVO(id = "1", name = "Testing", "Testing URL"),
            CategoryVO(id = "2", name = "Testing", "Testing URL"),
        )

        coEvery { categoriesRepo?.fetchCategories() } returns DataResult.Success(response)

        categoriesRepo?.let {
            val fetchCategories = FetchCategories(it)
            val expected = fetchCategories.invoke()
            assertThat(expected).isEqualTo(DataResult.Success(result))
        }
    }

    @Test
    fun `fetch categories error`() = runTest {
        coEvery { categoriesRepo?.fetchCategories() } returns DataResult.Failed(NetworkError.SomethingWrong)
        categoriesRepo?.let {
            val fetchCategories = FetchCategories(it)
            val expected = fetchCategories.invoke()
            assertThat(expected).isEqualTo(DataResult.Failed(NetworkError.SomethingWrong))
        }
    }
}