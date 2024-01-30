package com.example

import com.example.models.ApiResponse
import com.example.plugins.*
import com.example.repository.HeroRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.*

class ApplicationTest {
    private val heroRepository:HeroRepository by inject(HeroRepository::class.java)
 
    @Test
    fun `access root endpoint , assert correct information`() = testApplication() {
        application() {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to Boruto API!", bodyAsText())
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun `access all heroes endpoint , assert correct information`() = testApplication() {
        application() {
            configureRouting()
        }
        client.get("/boruto/heroes").apply {
            assertEquals(HttpStatusCode.OK, status)
            val expected = ApiResponse(
                success = true,
                message = "ok",
                prevPage = null,
                nextPage = 2,
                heroes = heroRepository.page1
            )
            val response = client.get("/boruto/heroes")
            val actual =Json.decodeFromString<ApiResponse>(response.toString())

            assertEquals(expected, actual)
        }
    }
}
