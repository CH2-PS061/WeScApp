package id.wildexplorerscompanion.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import id.wildexplorerscompanion.data.retrofit.response.RegisterResponse
import id.wildexplorerscompanion.ui.login.LoginViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val repository: WildRepository): ViewModel() {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse


    fun getRegister(name: String,email:String, password: String){
        viewModelScope.launch {
            try {
                val registerResponse = repository.register(name, email, password)
                _registerResponse.postValue(registerResponse)
                Log.d(TAG, "getRegister: $registerResponse")
            }catch (e: HttpException){
                val jsonString = e.response()?.errorBody()?.string()
                try {
                    val errorBody = Gson().fromJson(jsonString, RegisterResponse::class.java)
                    val errorMessage = errorBody.message
                    _registerResponse.postValue(errorBody)
                    Log.d(TAG, "getLogin: $errorMessage")
                }catch (ex: JsonSyntaxException){
                    Log.e(TAG, "message: $jsonString" )
                }
            }
        }
    }

    companion object{
        private val TAG = RegisterViewModel::class.simpleName
    }
}