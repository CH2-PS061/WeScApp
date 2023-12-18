package id.wildexplorerscompanion.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.DeleteResponse
import kotlinx.coroutines.launch

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
            val deleteResponse = repository.delete(token, email, password)
            _deleteAccount.postValue(deleteResponse)
        }
    }
}