package utn.frba.mobile.moodmatch.repository

import utn.frba.mobile.moodmatch.data.model.LoginRequest
import utn.frba.mobile.moodmatch.data.network.RetrofitServiceFactory

interface UserRepository {
    suspend fun login(email: String, password: String): Pair<Boolean, String>
}

class UserRepositoryImp : UserRepository {
    private val apiService = RetrofitServiceFactory.api

    override suspend fun login(email: String, password: String): Pair<Boolean, String> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                Pair(true, response.body()?.msg ?: "msg")
            } else {
                Pair(false, response.body()?.error ?: "Error")
            }
        } catch (e: Exception) {
            Pair(false, e.message ?: "Error desconocido")
        }
    }
}