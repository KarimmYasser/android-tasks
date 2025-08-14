# Task Management App - Room Implementation

## Suspend DAO vs Flow DAO

**Suspend functions** return data once when called, providing a snapshot of the current state. They're perfect for one-time operations like inserting data or fetching data that won't change during the UI lifecycle.

**Flow functions** emit data continuously whenever the underlying database changes, making them ideal for observing data that updates frequently. Flow automatically triggers UI updates when data changes, ensuring your interface stays synchronized with the database.

**LiveData functions** are similar to Flow but are lifecycle-aware and automatically pause/resume based on the component's lifecycle state, making them perfect for Activities and Fragments.

## Key Differences

- **suspend fun getAllProjectsOnce()**: Returns `List<Project>` once
- **fun getAllProjectsFlow()**: Returns `Flow<List<Project>>` that emits on every database change
- **fun getAllProjectsLiveData()**: Returns `LiveData<List<Project>>` with lifecycle awareness

Choose suspend for one-time operations, Flow for reactive programming with ViewModels, and LiveData for direct Fragment/Activity observation.