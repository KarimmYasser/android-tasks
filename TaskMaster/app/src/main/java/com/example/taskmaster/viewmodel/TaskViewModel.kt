import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
  private val database = TaskDatabase.getDatabase(application)
  private val projectDao = database.projectDao()
  private val taskDao = database.taskDao()

  // LiveData for projects
  val allProjectsLiveData: LiveData<List<Project>> = projectDao.getAllProjectsLiveData()

  // Flow for tasks in a specific project
  private val _selectedProjectId = MutableLiveData<Int>()
  val selectedProjectId: LiveData<Int> = _selectedProjectId

  @OptIn(ExperimentalCoroutinesApi::class)
  val tasksFlow: Flow<List<Task>> = _selectedProjectId.asFlow()
      .filterNotNull()
      .flatMapLatest { projectId ->
          taskDao.getTasksForProject(projectId)
      }

  fun selectProject(projectId: Int) {
      _selectedProjectId.value = projectId
  }

  // Test suspend vs flow methods
  suspend fun testSuspendAndFlow() {
      // Test suspend version
      val suspendProjects = projectDao.getAllProjectsOnce()
      Log.d("DAO_TEST", "Suspend projects: $suspendProjects")

      // Test Flow version - collect for a few emissions
      val job = viewModelScope.launch {
          projectDao.getAllProjectsFlow()
              .take(3) // Only take first 3 emissions
              .collect { projects ->
                  Log.d("DAO_TEST", "Flow emission: $projects")
              }
      }
  }
}