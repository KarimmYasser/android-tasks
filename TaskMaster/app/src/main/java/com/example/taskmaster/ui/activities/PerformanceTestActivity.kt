import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class PerformanceTestActivity : AppCompatActivity() {
  private lateinit var database: TaskDatabase
  private lateinit var performanceHelper: PerformanceTestHelper

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      
      database = TaskDatabase.getDatabase(this)
      performanceHelper = PerformanceTestHelper(database.projectDao())
      
      // Run performance tests
      lifecycleScope.launch {
          // Insert some test data first
          setupTestData()
          
          // Run the comparison
          val result = performanceHelper.compareQueryPerformance(100)
          
          Log.d("PERF_SUMMARY", "Performance Test Results:")
          Log.d("PERF_SUMMARY", "Room Total Time: ${result.roomQueryTimeNs}ns")
          Log.d("PERF_SUMMARY", "Raw Total Time: ${result.rawQueryTimeNs}ns")
          Log.d("PERF_SUMMARY", "Iterations: ${result.iterations}")
      }
  }

  private suspend fun setupTestData() {
      val userDao = database.userDao()
      val projectDao = database.projectDao()
      val taskDao = database.taskDao()

      // Create test users
      val user1Id = userDao.insertUser(User(name = "Test User 1", email = "user1@test.com")).toInt()
      val user2Id = userDao.insertUser(User(name = "Test User 2", email = "user2@test.com")).toInt()

      // Create projects with varying numbers of tasks
      repeat(10) { projectIndex ->
          val projectId = projectDao.insertProject(
              Project(title = "Test Project $projectIndex", ownerId = if (projectIndex % 2 == 0) user1Id else user2Id)
          ).toInt()

          // Some projects get many tasks, others get few
          val taskCount = when {
              projectIndex < 3 -> 1 // Projects 0-2 get 1 task
              projectIndex < 6 -> 5 // Projects 3-5 get 5 tasks  
              else -> 2 // Projects 6-9 get 2 tasks
          }

          repeat(taskCount) { taskIndex ->
              taskDao.insertTask(
                  Task(
                      description = "Task $taskIndex for Project $projectIndex",
                      projectId = projectId,
                      completed = taskIndex % 3 == 0
                  )
              )
          }
      }

      Log.d("PERF_SETUP", "Test data setup complete: 10 projects with varying task counts")
  }
}