import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import kotlin.math.roundToInt

class PerformanceTestHelper(private val projectDao: ProjectDao) {
    
    data class PerformanceResult(
        val roomQueryTimeNs: Long,
        val rawQueryTimeNs: Long,
        val iterations: Int
    )

    suspend fun compareQueryPerformance(iterations: Int = 100): PerformanceResult {
        val minTaskCount = 3
        
        // Warm up the database
        repeat(5) {
            projectDao.getAllProjectsOnce()
        }

        // Test Room @Query
        val roomStartTime = System.nanoTime()
        repeat(iterations) {
            projectDao.getProjectsWithMoreThanNTasks(minTaskCount)
        }
        val roomEndTime = System.nanoTime()
        val roomTotalTime = roomEndTime - roomStartTime

        // Test @RawQuery
        val rawQuery: SupportSQLiteQuery = SimpleSQLiteQuery(
            """
            SELECT p.* FROM projects p 
            INNER JOIN tasks t ON p.id = t.project_id 
            GROUP BY p.id 
            HAVING COUNT(t.id) > ?
            """.trimIndent(),
            arrayOf(minTaskCount)
        )

        val rawStartTime = System.nanoTime()
        repeat(iterations) {
            projectDao.getProjectsWithMoreThanNTasksRaw(rawQuery)
        }
        val rawEndTime = System.nanoTime()
        val rawTotalTime = rawEndTime - rawStartTime

        // Log results
        val avgRoomTime = roomTotalTime / iterations
        val avgRawTime = rawTotalTime / iterations
        
        Log.d("PERF", "Room query average: ${avgRoomTime}ns")
        Log.d("PERF", "Raw query average: ${avgRawTime}ns")
        Log.d("PERF", "Room query total: ${roomTotalTime}ns")
        Log.d("PERF", "Raw query total: ${rawTotalTime}ns")
        Log.d("PERF", "Iterations: $iterations")

        val performanceDiff = if (avgRoomTime > avgRawTime) {
            "Raw query is ${((avgRoomTime - avgRawTime) * 100.0 / avgRoomTime).roundToInt()}% faster"
        } else {
            "Room query is ${((avgRawTime - avgRoomTime) * 100.0 / avgRawTime).roundToInt()}% faster"
        }
        Log.d("PERF", performanceDiff)

        return PerformanceResult(roomTotalTime, rawTotalTime, iterations)
    }

    // Additional complex query examples
    suspend fun testComplexQueries() {
        // Room query with joins and aggregation
        val roomComplexStart = System.nanoTime()
        val roomComplexQuery = """
            SELECT p.*, COUNT(t.id) as task_count, 
                   COUNT(CASE WHEN t.completed = 1 THEN 1 END) as completed_tasks
            FROM projects p 
            LEFT JOIN tasks t ON p.id = t.project_id 
            GROUP BY p.id
            ORDER BY task_count DESC
        """
        // This would be implemented as a custom query in the DAO
        val roomComplexEnd = System.nanoTime()
        
        // Raw query equivalent
        val rawComplexStart = System.nanoTime()
        val complexRawQuery = SimpleSQLiteQuery(roomComplexQuery)
        // projectDao.complexProjectStatsRaw(complexRawQuery)
        val rawComplexEnd = System.nanoTime()
        
        Log.d("PERF", "Complex Room query: ${roomComplexEnd - roomComplexStart}ns")
        Log.d("PERF", "Complex Raw query: ${rawComplexEnd - rawComplexStart}ns")
    }
}