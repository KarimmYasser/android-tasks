import androidx.room.*

@Entity(
    tableName = "project_task_crossref",
    primaryKeys = ["project_id", "task_id"],
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProjectTaskCrossRef(
    @ColumnInfo(name = "project_id")
    val projectId: Int,
    @ColumnInfo(name = "task_id")
    val taskId: Int
)