import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    @Query("SELECT * FROM tasks WHERE project_id = :projectId")
    fun getTasksForProject(projectId: Int): Flow<List<Task>>

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    @Query("UPDATE tasks SET completed = :completed WHERE id = :taskId")
    suspend fun updateTaskCompletion(taskId: Int, completed: Boolean)
}