import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProjectRepository(private val projectDao: ProjectDao) {
    
  suspend fun testProjectWithTasks(projectId: Int) {
      val result = projectDao.getProjectWithTasks(projectId)
      Log.d("DB_TEST", "Project with Tasks: $result")
      
      result?.let { projectWithTasks ->
          Log.d("DB_TEST", "Project: ${projectWithTasks.project.title}")
          Log.d("DB_TEST", "Tasks count: ${projectWithTasks.tasks.size}")
          projectWithTasks.tasks.forEach { task ->
              Log.d("DB_TEST", "  - Task: ${task.description}")
          }
      }
  }

  suspend fun testSuspendVsFlow() {
      // Test suspend version
      val suspendProjects = projectDao.getAllProjectsOnce()
      Log.d("DAO_TEST", "Suspend projects: $suspendProjects")
      
      // Let it collect for a few seconds
      delay(3000)
  }
}