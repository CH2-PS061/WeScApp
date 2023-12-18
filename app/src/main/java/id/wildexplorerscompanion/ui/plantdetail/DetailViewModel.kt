package id.wildexplorerscompanion.ui.plantdetail

import androidx.lifecycle.ViewModel
import id.wildexplorerscompanion.data.repo.WildRepository

class DetailViewModel(private val repository: WildRepository): ViewModel() {

    fun getAllPlant() = repository.getAllPlant()

    fun getById(plantId: String) = repository.getById(plantId)
}