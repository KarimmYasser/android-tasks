import androidx.room.*
import java.util.Date

@Entity(
    tableName = "projects",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["owner_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["owner_id"])]
)
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "owner_id")
    val ownerId: Int,
    @ColumnInfo(name = "created_date")
    val createdDate: Date = Date(),
    @ColumnInfo(name = "tags")
    val tags: List<String> = emptyList()
)