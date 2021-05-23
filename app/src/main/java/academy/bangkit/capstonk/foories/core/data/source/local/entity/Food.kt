package academy.bangkit.capstonk.foories.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Double,
    val addedAt: Date
)