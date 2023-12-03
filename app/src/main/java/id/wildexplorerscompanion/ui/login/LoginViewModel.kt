package id.wildexplorerscompanion.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: WildRepository): ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun getLogin(email: String, password: String){
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                saveSession(UserModel(
                    response.data.id,
                    response.data.name,
                    response.data.email
                ))
                _loginResponse.postValue(response)
            }catch (e: HttpException){
                val jsonString = e.response()?.errorBody()?.string()
                try {
                val errorBody = Gson().fromJson(jsonString,LoginResponse::class.java)
                val errorMessage = errorBody.message
                _loginResponse.postValue(errorBody)
                Log.d(TAG, "getLogin: $errorMessage")
                }catch (ex: JsonSyntaxException){
                    Log.e(TAG, "message: $jsonString" )
                }
            }
        }
    }

    private fun saveSession(user: UserModel){
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }
}