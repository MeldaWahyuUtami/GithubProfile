package id.ac.polbeng.depandienda.githubprofile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.polbeng.depandienda.githubprofile.models.GithubUser
import id.ac.polbeng.depandienda.githubprofile.services.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _githubUser = MutableLiveData<GithubUser?>()
    val githubUser: LiveData<GithubUser?> = _githubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGithubUser(username)
        client.enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _githubUser.value = response.body()
                } else {
                    _githubUser.value = null
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                _isLoading.value = false
                _githubUser.value = null
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
