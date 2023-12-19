package id.wildexplorerscompanion.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM edible_plants WHERE plant_id =:plantId")
    fun getById(plantId: String): LiveData<EdiblePlantsEntity>

    @Query("SELECT * FROM edible_plants")
    fun getAllPlant(): LiveData<List<EdiblePlantsEntity>>

}