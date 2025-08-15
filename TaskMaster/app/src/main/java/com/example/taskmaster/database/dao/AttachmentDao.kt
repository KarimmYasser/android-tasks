import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AttachmentDao {
    @Query("SELECT * FROM attachments ORDER BY id DESC")
    suspend fun getAllAttachments(): List<Attachment>
    
    @Query("SELECT * FROM attachments WHERE task_id = :taskId")
    suspend fun getAttachmentsByTask(taskId: Int): List<Attachment>
    
    @Query("SELECT * FROM attachments WHERE task_id = :taskId")
    fun getAttachmentsByTaskFlow(taskId: Int): Flow<List<Attachment>>
    
    @Query("SELECT * FROM attachments WHERE id = :attachmentId")
    suspend fun getAttachmentById(attachmentId: Int): Attachment?
    
    @Query("SELECT SUM(file_size) FROM attachments WHERE task_id = :taskId")
    suspend fun getTotalFileSizeForTask(taskId: Int): Long?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: Attachment): Long
    
    @Update
    suspend fun updateAttachment(attachment: Attachment)
    
    @Delete
    suspend fun deleteAttachment(attachment: Attachment)
    
    @Query("DELETE FROM attachments WHERE id = :attachmentId")
    suspend fun deleteAttachmentById(attachmentId: Int)
    
    @Query("DELETE FROM attachments WHERE task_id = :taskId")
    suspend fun deleteAllAttachmentsForTask(taskId: Int)
}