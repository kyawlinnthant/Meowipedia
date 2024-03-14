package com.everest.data.categories.repository

import com.everest.data.repository.HomeApiRepository
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HomeApiRepositoryTest {

    private lateinit var repo : HomeApiRepository

    @BeforeEach
    fun setup() {
        repo = mockk(relaxed = true)
    }


}
