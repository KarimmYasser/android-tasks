import androidx.room.*

@Dao
interface AttachmentDao {
    @Insert
    suspend fun insertAttachment(attachment: Attachment): Long

    @Update
    suspend fun updateAttachment(attachment: Attachment)

    @Delete
    suspend fun deleteAttachment(attachment: Attachment)

    @Query("SELECT * FROM attachments WHERE id = :attachmentId")
    suspend fun getAttachmentById(attachmentId: Int): Attachment?

    @Query("SELECT * FROM attachments WHERE task_id = :taskId")
    suspend fun getAttachmentsForTask(taskId: Int): List<Attachment>

    @Query("DELETE FROM attachments WHERE task_id = :taskId")
    suspend fun deleteAttachmentsForTask(taskId: Int)
}