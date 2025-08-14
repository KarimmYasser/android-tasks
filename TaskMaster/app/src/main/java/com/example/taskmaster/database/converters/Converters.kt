import androidx.room.TypeConverter
import java.util.Date

class Converters {
    
    // List<String> ↔ String converter
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return if (value.isEmpty()) {
            emptyList()
        } else {
            value.split(",").map { it.trim() }
        }
    }

    // Date ↔ Long converter
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}