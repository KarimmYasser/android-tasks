import androidx.room.*

@Entity(
    tableName = "attachments",
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["task_id"])]
)
data class Attachment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "file_path")
    val filePath: String,
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "file_name")
    val fileName: String,
    @ColumnInfo(name = "file_size")
    val fileSize: Long
)