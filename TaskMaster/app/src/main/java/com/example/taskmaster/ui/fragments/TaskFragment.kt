import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TaskFragment : Fragment() {
  private lateinit var viewModel: TaskViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      
      setupObservers()
  }

  private fun setupObservers() {
      // Observe LiveData for projects
      viewModel.allProjectsLiveData.observe(viewLifecycleOwner) { projects ->
          Log.d("FRAGMENT_OBSERVER", "LiveData projects updated: ${projects.size} projects")
          projects.forEach { project ->
              Log.d("FRAGMENT_OBSERVER", "  - Project: ${project.title}")
          }
          // Update your RecyclerView adapter here
          // projectsAdapter.submitList(projects)
      }

      // Collect Flow for tasks
      viewLifecycleOwner.lifecycleScope.launch {
          viewModel.tasksFlow.collectLatest { tasks ->
              Log.d("FRAGMENT_OBSERVER", "Flow tasks updated: ${tasks.size} tasks")
              tasks.forEach { task ->
                  Log.d("FRAGMENT_OBSERVER", "  - Task: ${task.description} (completed: ${task.completed})")
              }
              // Update your tasks RecyclerView adapter here
              // tasksAdapter.submitList(tasks)
          }
      }

      // Select a project to start observing its tasks
      viewModel.selectProject(1)

      // Test the suspend vs flow functionality
      lifecycleScope.launch {
          viewModel.testSuspendAndFlow()
      }
  }
}