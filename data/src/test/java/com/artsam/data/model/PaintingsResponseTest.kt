package com.artsam.data.model

import com.artsam.data.entity.Painting
import com.google.gson.GsonBuilder
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test


/**
 * Tests for [PaintingsResponse]
 *
 * @author Artem Samoshkin on 24.04.2023
 */
class PaintingsResponseTest {
    @Test
    fun parsingOfPaintingsResponse() {
        val gson = GsonBuilder()
            //.setDateFormat(DateUtils.STANDARD_SERVER_FORMAT)
            //.setLenient()
            .create()

        val expected = PaintingsResponse(
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

        val json = """
            {
                "paintings": [
                    {
                        "id": "af7c1fe6-d669-414e-b066-e9733f0de7a8",
                        "createdAt": "2022-11-27T19:39:02.471Z",
                        "surface": "Canvas",
                        "paint": "Oil",
                        "image": "https://github.com/SamoshkinR-Tem/mmdd.pictures/blob/master/4x4/Ghovardhan.jpg"
                    },
                    {
                        "id": "08c71152-c552-42e7-b094-f510ff44e9cb",
                        "createdAt": "2022-11-27T19:39:02.471Z",
                        "surface": "WoodenBoard",
                        "paint": "Acrylic",
                        "image": "https://github.com/SamoshkinR-Tem/mmdd.pictures/blob/master/4x4/Red.jpg"
                    }
                ]
            }    
        """.trimIndent()

        val actual = gson.fromJson(json, PaintingsResponse::class.java)

        // Check
        MatcherAssert.assertThat(actual, CoreMatchers.equalTo(expected))
    }
}
