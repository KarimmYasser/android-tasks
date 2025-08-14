import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert
    suspend fun insertProject(project: Project): Long

    @Update
    suspend fun updateProject(project: Project)

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectById(projectId: Int): Project?

    // Suspend version - one-time snapshot
    @Query("SELECT * FROM projects")
    suspend fun getAllProjectsOnce(): List<Project>

    // Flow version - emits updates when data changes
    @Query("SELECT * FROM projects")
    fun getAllProjectsFlow(): Flow<List<Project>>

    // LiveData version
    @Query("SELECT * FROM projects")
    fun getAllProjectsLiveData(): LiveData<List<Project>>

    // ProjectWithTasks using @Embedded and @Relation
    @Transaction
    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectWithTasks(projectId: Int): ProjectWithTasks?

    @Transaction
    @Query("SELECT * FROM projects")
    suspend fun getAllProjectsWithTasks(): List<ProjectWithTasks>

    // Complex query for performance testing
    @Query("""
        SELECT p.* FROM projects p 
        INNER JOIN tasks t ON p.id = t.project_id 
        GROUP BY p.id 
        HAVING COUNT(t.id) > :minTaskCount
    """)
    suspend fun getProjectsWithMoreThanNTasks(minTaskCount: Int): List<Project>

    // Raw query version for performance comparison
    @RawQuery
    suspend fun getProjectsWithMoreThanNTasksRaw(query: SupportSQLiteQuery): List<Project>
}
