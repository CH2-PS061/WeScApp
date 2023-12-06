package id.wildexplorerscompanion.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.RegisterResponse
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

            }
        }
    }

    companion object{
        private val TAG = RegisterViewModel::class.simpleName
    }
}