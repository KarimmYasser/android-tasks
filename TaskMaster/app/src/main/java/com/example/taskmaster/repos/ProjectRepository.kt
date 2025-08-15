import android.util.Log
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projectDao: ProjectDao,
                        private val taskDao: TaskDao
) {
    suspend fun getAllProjects(): List<Project> {
        return projectDao.getAllProjectsOnce()
    }
    
    fun getTasksForProject(projectId: Int): Flow<List<Task>> {
        return taskDao.getTasksByProjectFlow(projectId)
    }
    
  suspend fun testProjectWithTasks(projectId: Int) {
      val result = projectDao.getProjectWithTasks(projectId)
      result?.let { projectWithTasks ->
          Log.d("DB_TEST", "Project with Tasks: ${projectWithTasks.project.title}")
          Log.d("DB_TEST", "Tasks count: ${projectWithTasks.tasks.size}")
          projectWithTasks.tasks.forEach { task ->
              Log.d("DB_TEST", "  - Task: ${task.description}")
          }
      } ?: Log.d("DB_TEST", "No project found with ID: $projectId")
  }
  
  suspend fun testTypeConverters() {
      val project = Project(
          title = "Test Project",
          ownerId = 1,
          tags = listOf("mobile", "android", "kotlin")
      )
      
      val projectId = projectDao.insertProject(project)
      Log.d("DB_TEST", "Inserted project with tags: ${project.tags}")

      val retrievedProject = projectDao.getProjectWithTasks(projectId.toInt())
      retrievedProject?.let {
          Log.d("DB_TEST", "Retrieved project tags: ${it.project.tags}")
          Log.d("DB_TEST", "Date converter test - Created: ${it.project.createdDate}")
      }
  }
}