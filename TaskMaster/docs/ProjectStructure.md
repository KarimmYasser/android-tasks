# Task Management App - Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/taskmanager/
│   │   │   ├── MainActivity.kt
│   │   │   ├── database/
│   │   │   │   ├── TaskDatabase.kt
│   │   │   │   ├── entities/
│   │   │   │   │   ├── User.kt
│   │   │   │   │   ├── Project.kt
│   │   │   │   │   ├── Task.kt
│   │   │   │   │   ├── Attachment.kt
│   │   │   │   │   ├── ProjectTaskCrossRef.kt
│   │   │   │   │   ├── ProjectWithTasks.kt
│   │   │   │   │   └── UserWithProjects.kt
│   │   │   │   ├── dao/
│   │   │   │   │   ├── UserDao.kt
│   │   │   │   │   ├── ProjectDao.kt
│   │   │   │   │   ├── TaskDao.kt
│   │   │   │   │   └── AttachmentDao.kt
│   │   │   │   └── converters/
│   │   │   │       └── Converters.kt
│   │   │   ├── repository/
│   │   │   │   ├── TaskRepository.kt
│   │   │   │   └── UserRepository.kt
│   │   │   ├── viewmodel/
│   │   │   │   ├── TaskViewModel.kt
│   │   │   │   └── ProjectViewModel.kt
│   │   │   ├── ui/
│   │   │   │   ├── fragments/
│   │   │   │   │   ├── TaskFragment.kt
│   │   │   │   │   ├── ProjectListFragment.kt
│   │   │   │   │   └── ProjectDetailFragment.kt
│   │   │   │   ├── adapters/
│   │   │   │   │   ├── ProjectAdapter.kt
│   │   │   │   │   ├── TaskAdapter.kt
│   │   │   │   │   └── AttachmentAdapter.kt
│   │   │   │   └── activities/
│   │   │   │       ├── MainActivity.kt
│   │   │   │       └── PerformanceTestActivity.kt
│   │   │   ├── utils/
│   │   │   │   ├── DatabaseTester.kt
│   │   │   │   ├── PerformanceTestHelper.kt
│   │   │   │   └── Constants.kt
│   │   │   └── di/ (Optional - for Dependency Injection)
│   │   │       ├── DatabaseModule.kt
│   │   │       └── RepositoryModule.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── fragment_task_list.xml
│   │   │   │   ├── fragment_project_detail.xml
│   │   │   │   ├── item_project.xml
│   │   │   │   ├── item_task.xml
│   │   │   │   └── item_attachment.xml
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   ├── colors.xml
│   │   │   │   └── themes.xml
│   │   │   └── drawable/
│   │   │       ├── ic_project.xml
│   │   │       ├── ic_task.xml
│   │   │       └── ic_attachment.xml
│   │   └── AndroidManifest.xml
│   └── test/
│       └── java/com/taskmanager/
│           ├── database/
│           │   ├── TaskDatabaseTest.kt
│           │   ├── ProjectDaoTest.kt
│           │   └── ConvertersTest.kt
│           ├── repository/
│           │   └── TaskRepositoryTest.kt
│           └── utils/
│               └── PerformanceTestHelperTest.kt
├── build.gradle.kts (Module)
├── proguard-rules.pro
└── docs/
    ├── STORAGE_MATRIX.md
    ├── README.md
    ├── PerformanceReport.md
    └── schema.png

build.gradle.kts (Project level)
settings.gradle.kts
gradle.properties
```

## 📁 **Directory Breakdown**

### **Core Database Layer**
- **`database/entities/`** - All Room entities and relationship classes
- **`database/dao/`** - Data Access Objects with suspend/Flow methods
- **`database/converters/`** - Type converters for complex data types
- **`database/TaskDatabase.kt`** - Main Room database class

### **Business Logic Layer**
- **`repository/`** - Repository pattern implementation for data access
- **`viewmodel/`** - ViewModels exposing LiveData and handling UI logic

### **UI Layer**
- **`ui/fragments/`** - Fragment classes with LiveData/Flow observers
- **`ui/adapters/`** - RecyclerView adapters for lists
- **`ui/activities/`** - Activity classes including performance testing

### **Utility & Testing**
- **`utils/`** - Helper classes for database testing and performance
- **`test/`** - Unit tests for DAOs, repositories, and utilities

## 🔧 **Key Files by Requirement**

| Requirement | Files |
|-------------|-------|
| **2.1 Storage Matrix** | `docs/STORAGE_MATRIX.md` |
| **2.2 Schema Design** | `database/entities/*.kt`, `docs/schema.png` |
| **2.3 TypeConverters** | `database/converters/Converters.kt` |
| **2.4 Suspend vs Flow** | `database/dao/ProjectDao.kt`, `README.md` |
| **2.5 LiveData/Flow UI** | `ui/fragments/TaskFragment.kt`, `viewmodel/TaskViewModel.kt` |
| **2.6 Performance Testing** | `utils/PerformanceTestHelper.kt`, `docs/PerformanceReport.md` |

## 📦 **Dependencies (build.gradle.kts)**

```kotlin
dependencies {
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    
    // Fragment & Activity
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.activity:activity-ktx:1.8.2")
    
    // Testing
    testImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}
```

## 🚀 **Setup Instructions**

1. **Create Android Project** with Kotlin support
2. **Add dependencies** to `build.gradle.kts`
3. **Copy entity classes** to `database/entities/`
4. **Implement DAOs** in `database/dao/`
5. **Create database class** with proper annotations
6. **Set up ViewModels** and Fragments for UI observation
7. **Add performance testing** utilities
8. **Create documentation** files in `docs/`

This structure follows Android Architecture Guidelines and separates concerns properly for maintainable code.