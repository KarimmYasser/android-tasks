import androidx.room.*

data class UserWithProjects(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "owner_id"
    )
    val projects: List<Project>
)