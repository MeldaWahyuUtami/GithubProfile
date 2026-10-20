package id.ac.polbeng.depandienda.githubprofile.services

import id.ac.polbeng.depandienda.githubprofile.models.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    fun getGithubUser(
        @Path("username") username: String
    ): Call<GithubUser>
}
