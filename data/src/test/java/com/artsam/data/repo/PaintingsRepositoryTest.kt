package com.artsam.data.repo

import com.artsam.data.api.PaintingsApi
import com.artsam.data.entity.Painting
import com.artsam.data.model.PaintingsResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class) // required for 'runTest' method
class PaintingsRepositoryTest {

    private lateinit var api: PaintingsApi
    private lateinit var repo: PaintingsRepository

    @Before
    fun setup() {
        api = mock(PaintingsApi::class.java)
        repo = PaintingsRepository(api = lazy { api })
    }

    @Test
    fun `fetching data with mock PaintingRepo`() = runTest {
        // arrange
        val expectedData = PaintingsResponse(
            listOf(
                Painting(
                    "af7c1fe6-d669-414e-b066-e9733f0de7a8",
                    "2022-11-27T19:39:02.471Z",
                    "Canvas",
                    "Oil",
                    "https://github.com/SamoshkinR-Tem/mmdd.pictures/blob/master/4x4/Ghovardhan.jpg",
                ),
                Painting(
                    "08c71152-c552-42e7-b094-f510ff44e9cb",
                    "2022-11-27T19:39:02.471Z",
                    "WoodenBoard",
                    "Acrylic",
                    "https://github.com/SamoshkinR-Tem/mmdd.pictures/blob/master/4x4/Red.jpg",
                )
            )
        )
        `when`(
            api.fetch(
                "master",
                "paintings-list.json",
            )
        ).thenReturn(expectedData)

        // act
        val flow = repo.fetch() // must be exactly as described above in `when`

        // assert
        flow.collect { data ->
            assertEquals(expectedData.paintings, data)
        }
    }

    @Test
    fun `fetching data with real PaintingRepo`() = runTest {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://github.com/SamoshkinR-Tem/mmdd.pictures/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(PaintingsApi::class.java)
        repo = PaintingsRepository(api = lazy { api })

        val expectedData = listOf(
            Painting(
                "af7c1fe6-d669-414e-b066-e9733f0de7a8",
                "2022-11-27T19:39:02.471Z",
                "Canvas",
                "Oil",
                "https://github.com/SamoshkinR-Tem/mmdd.pictures/blob/master/4x4/Ghovardhan.jpg",
            ),
            Painting(
                "08c71152-c552-42e7-b094-f510ff44e9cb",
                "2022-11-27T19:39:02.471Z",
                "WoodenBoard",
                "Acrylic",
                "https://github.com/SamoshkinR-Tem/mmdd.pictures/blob/master/4x4/Red.jpg",
            )
        )

        // act
        val flow = repo.fetch(path = "test/paintings-test-list.json")

        // assert
        flow.collect { data ->
            assertEquals(expectedData, data)
        }
    }
}

//.baseUrl("https://raw.githubusercontent.com/SamoshkinR-Tem/mmdd.pictures/")