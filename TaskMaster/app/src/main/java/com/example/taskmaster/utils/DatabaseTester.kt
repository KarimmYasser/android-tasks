import android.util.Log

class DatabaseTester {
  suspend fun insertSampleData(database: TaskDatabase) {
      val userDao = database.userDao()
      val projectDao = database.projectDao()
      val taskDao = database.taskDao()
      val attachmentDao = database.attachmentDao()

      // Insert Users
      val user1 = User(name = "John Doe", email = "john@example.com")
      val user2 = User(name = "Jane Smith", email = "jane@example.com")
      
      val userId1 = userDao.insertUser(user1).toInt()
      val userId2 = userDao.insertUser(user2).toInt()
      
      Log.d("DB_TEST", "Inserted: User(id=$userId1, name=${user1.name}, email=${user1.email})")
      Log.d("DB_TEST", "Inserted: User(id=$userId2, name=${user2.name}, email=${user2.email})")

      // Insert Projects
      val project1 = Project(title = "Mobile App Development", ownerId = userId1)
      val project2 = Project(title = "Website Redesign", ownerId = userId2)
      
      val projectId1 = projectDao.insertProject(project1).toInt()
      val projectId2 = projectDao.insertProject(project2).toInt()
      
      Log.d("DB_TEST", "Inserted: Project(id=$projectId1, title=${project1.title}, ownerId=${project1.ownerId})")
      Log.d("DB_TEST", "Inserted: Project(id=$projectId2, title=${project2.title}, ownerId=${project2.ownerId})")

      // Insert Tasks
      val task1 = Task(description = "Design UI mockups", projectId = projectId1)
      val task2 = Task(description = "Implement authentication", projectId = projectId1)
      val task3 = Task(description = "Create wireframes", projectId = projectId2)
      
      val taskId1 = taskDao.insertTask(task1).toInt()
      val taskId2 = taskDao.insertTask(task2).toInt()
      val taskId3 = taskDao.insertTask(task3).toInt()
      
      Log.d("DB_TEST", "Inserted: Task(id=$taskId1, description=${task1.description}, projectId=${task1.projectId})")
      Log.d("DB_TEST", "Inserted: Task(id=$taskId2, description=${task2.description}, projectId=${task2.projectId})")
      Log.d("DB_TEST", "Inserted: Task(id=$taskId3, description=${task3.description}, projectId=${task3.projectId})")

      // Insert Attachments
      val attachment1 = Attachment(
          filePath = "/storage/emulated/0/mockups/ui_design.png",
          taskId = taskId1,
          fileName = "ui_design.png",
          fileSize = 2048576L
      )
      val attachment2 = Attachment(
          filePath = "/storage/emulated/0/docs/auth_spec.pdf",
          taskId = taskId2,
          fileName = "auth_spec.pdf",
          fileSize = 1024000L
      )
      
      val attachmentId1 = attachmentDao.insertAttachment(attachment1).toInt()
      val attachmentId2 = attachmentDao.insertAttachment(attachment2).toInt()
      
      Log.d("DB_TEST", "Inserted: Attachment(id=$attachmentId1, fileName=${attachment1.fileName}, taskId=${attachment1.taskId})")
      Log.d("DB_TEST", "Inserted: Attachment(id=$attachmentId2, fileName=${attachment2.fileName}, taskId=${attachment2.taskId})")
  }
}