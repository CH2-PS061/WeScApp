package id.wildexplorerscompanion.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EdiblePlantsEntity::class], version = 1, exportSchema = false)
abstract class EdiblePlantsDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object{
        @Volatile
        private var INSTANCE: EdiblePlantsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): EdiblePlantsDatabase {
            if (INSTANCE == null) {
                synchronized(EdiblePlantsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EdiblePlantsDatabase::class.java,
                        "edibleplant_db")
                        .createFromAsset("database/EdiablePlant.db")
                        .build()
                }
            }
            return INSTANCE as EdiblePlantsDatabase
        }
    }
}