import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    suspend fun getAllTasks(): List<Task>
    
    @Query("SELECT * FROM tasks WHERE project_id = :projectId ORDER BY id DESC")
    fun getTasksByProjectFlow(projectId: Int): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE project_id = :projectId ORDER BY id DESC")
    suspend fun getTasksByProject(projectId: Int): List<Task>
    
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?
    
    @Query("SELECT * FROM tasks WHERE completed = :isCompleted")
    suspend fun getTasksByCompletionStatus(isCompleted: Boolean): List<Task>
    
    @Query("SELECT * FROM tasks WHERE due_date IS NOT NULL AND due_date < :currentDate AND completed = 0")
    suspend fun getOverdueTasks(currentDate: Long): List<Task>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long
    
    @Update
    suspend fun updateTask(task: Task)
    
    @Delete
    suspend fun deleteTask(task: Task)
    
    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)
    
    @Query("UPDATE tasks SET completed = :isCompleted WHERE id = :taskId")
    suspend fun updateTaskCompletionStatus(taskId: Int, isCompleted: Boolean)
}