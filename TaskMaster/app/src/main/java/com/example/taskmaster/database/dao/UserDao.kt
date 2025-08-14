import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserWithProjects(userId: Int): UserWithProjects?
}