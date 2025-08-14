import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val database: TaskDatabase) {
  private val projectDao = database.projectDao()
  private val taskDao = database.taskDao()

  // LiveData exposure
  fun getAllProjectsLiveData(): LiveData<List<Project>> = projectDao.getAllProjectsLiveData()

  // Flow exposure
  fun getTasksForProject(projectId: Int): Flow<List<Task>> = taskDao.getTasksForProject(projectId)

  // Suspend functions
  suspend fun insertProject(project: Project) = projectDao.insertProject(project)
  suspend fun getProjectWithTasks(projectId: Int) = projectDao.getProjectWithTasks(projectId)

  // Test logging function
  suspend fun testDataObservation() {
      // Test LiveData (converted to one-time fetch for testing)
      val projects = projectDao.getAllProjectsOnce()
      Log.d("REPOSITORY_TEST", "Projects from suspend: $projects")
  }
}