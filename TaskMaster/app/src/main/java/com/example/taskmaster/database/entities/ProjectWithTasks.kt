import androidx.room.*

data class ProjectWithTasks(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    val tasks: List<Task>
)