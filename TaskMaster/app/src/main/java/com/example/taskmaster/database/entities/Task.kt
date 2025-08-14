import androidx.room.*
import java.util.Date

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["project_id"])]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "project_id")
    val projectId: Int,
    @ColumnInfo(name = "due_date")
    val dueDate: Date? = null,
    @ColumnInfo(name = "completed")
    val completed: Boolean = false
)