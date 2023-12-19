package id.wildexplorerscompanion.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.DeleteResponse
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import id.wildexplorerscompanion.ui.login.LoginViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(private val repository: WildRepository): ViewModel() {

    private val _deleteAccount = MutableLiveData<DeleteResponse>()
    val deleteAccount: LiveData<DeleteResponse> = _deleteAccount
    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }


    fun getDelete(token: String, email: String, password: String) {
        viewModelScope.launch {
            try {
            val deleteResponse = repository.delete(token, email, password)
            _deleteAccount.postValue(deleteResponse)
            }catch (e: HttpException){
                val jsonString = e.response()?.errorBody()?.string()
                try {
                    val errorBody = Gson().fromJson(jsonString, DeleteResponse::class.java)
                    val errorMessage = errorBody.message
                    _deleteAccount.postValue(errorBody)
                    Log.d(TAG, "getDelete: $errorMessage")
                }catch (ex: JsonSyntaxException){
                    Log.e(TAG, "message: $jsonString" )
                }
            }
        }
    }

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }
}