package id.wildexplorerscompanion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse

class HomeViewModel(private val repository: WildRepository): ViewModel() {

    fun getSession(): LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }
}