package com.shatokhin.navigphotoofcats.data.network

import android.util.Log
import com.shatokhin.navigphotoofcats.data.models.*
import com.shatokhin.navigphotoofcats.utilites.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NetworkHttp {
    private val clientHttp = HttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun getRandomCats(count: Int): List<Cat>? {
        return try {
            val fullUrl = BASE_URL + URL_RANDOM_CAT + count
            val jsonString = clientHttp.get<String>(fullUrl)
            json.decodeFromString(jsonString)

        } catch (e: Exception) {
            null
        }
    }

    suspend fun voteUpCat(cat: Cat): ResultPostVote {
        return voteCat(cat, 1)
    }

    suspend fun voteDownCat(cat: Cat): ResultPostVote {
        return voteCat(cat, 0)
    }

    private suspend fun voteCat(cat: Cat, vote: Int): ResultPostVote {
        return try {
            val fullUrl = BASE_URL + VOTE_URL

            val obj = VotePost(cat.id, "0", vote)
            val objJson = json.encodeToString(obj)
            val resultPost = clientHttp.post<String>(fullUrl) {
                contentType(ContentType.Application.Json)
                header(NAME_HEADER_AUT, KEY_API)
                body = objJson
            }
            val respons = json.decodeFromString<ResultPostVote>(resultPost)
            respons
        } catch (e: Exception) {
            ResultPostVote(TEXT_RESPONSE_NETWORK_ERROR)
        }
    }

    suspend fun getVotes(): List<Vote>? {
        return try {
            val fullUrl = BASE_URL + VOTE_URL
            val jsonString = clientHttp.get<String>(fullUrl) {
                header(NAME_HEADER_AUT, KEY_API)
            }
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun deleteVote(idVote: Int): ResultDeleteVote {
        return try {
            val fullUrl = BASE_URL + VOTE_URL + "/${idVote}"
            Log.d(TAG_FOR_LOGCAT, fullUrl)
            val resultDelete = clientHttp.delete<String>(fullUrl) {
                header(NAME_HEADER_AUT, KEY_API)
            }
            val respons = json.decodeFromString<ResultDeleteVote>(resultDelete)
            respons
        } catch (e: Exception) {
            ResultDeleteVote(TEXT_RESPONSE_NETWORK_ERROR)
        }
    }

    suspend fun getCatById(idCat: String): Cat? { // не тестировалось!!!!!
        return try {
            val fullUrl = BASE_URL + URL_CAT_BY_ID + idCat
            val jsonString = clientHttp.get<String>(fullUrl) {
                header(NAME_HEADER_AUT, KEY_API)
            }
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            null
        }
    }
}