import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY created_date DESC")
    suspend fun getAllProjectsOnce(): List<Project>
    
    @Query("SELECT * FROM projects ORDER BY created_date DESC")
    fun getAllProjectsFlow(): Flow<List<Project>>
    
    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectWithTasks(projectId: Int): ProjectWithTasks?
    
    @Query("SELECT * FROM projects WHERE owner_id = :ownerId")
    fun getProjectsByOwner(ownerId: Int): Flow<List<Project>>
    
    @Query("""
        SELECT p.* FROM projects p 
        INNER JOIN tasks t ON p.id = t.project_id 
        GROUP BY p.id 
        HAVING COUNT(t.id) > :taskCount
        ORDER BY p.id
    """)
    suspend fun getProjectsWithMoreThanNTasks(taskCount: Int): List<Project>
    
    @RawQuery
    suspend fun getProjectsRawQuery(query: SupportSQLiteQuery): List<Project>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long
    
    @Update
    suspend fun updateProject(project: Project)
    
    @Delete
    suspend fun deleteProject(project: Project)
    
    @Query("DELETE FROM projects WHERE id = :projectId")
    suspend fun deleteProjectById(projectId: Int)
}