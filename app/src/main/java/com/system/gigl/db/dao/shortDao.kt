import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.system.gigl.models.Shorts

@Dao
interface ShortDao {
    @Query("SELECT * FROM shorts")
    suspend fun getAllShorts(): List<Shorts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(shorts: List<Shorts>)
}
