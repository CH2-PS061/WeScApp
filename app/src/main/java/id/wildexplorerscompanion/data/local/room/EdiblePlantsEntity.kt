package id.wildexplorerscompanion.data.local.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "edible_plants")
@Parcelize
data class EdiblePlantsEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "plant_id")
    val plant_id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

): Parcelable