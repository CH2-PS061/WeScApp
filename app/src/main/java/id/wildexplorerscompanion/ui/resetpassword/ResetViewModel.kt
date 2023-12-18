package id.wildexplorerscompanion.ui.resetpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.ChangePasswordResponse
import kotlinx.coroutines.launch

class ResetViewModel(private val repository: WildRepository): ViewModel() {

    private val _resetPassword = MutableLiveData<ChangePasswordResponse>()
    val resetPassword: LiveData<ChangePasswordResponse> = _resetPassword

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getResetPassword(token: String, email: String, currentPassword: String, newPassword: String){
        viewModelScope.launch {
            val resetResponse = repository.changePassword(token, email, currentPassword, newPassword)
            _resetPassword.postValue(resetResponse)
        }
    }
}