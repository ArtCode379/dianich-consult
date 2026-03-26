package dianichconsult.service.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dianichconsult.service.app.data.entity.BookingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    @Query("SELECT * FROM bookings")
    fun observeAll(): Flow<List<BookingEntity>>

    @Query("SELECT * FROM bookings WHERE serviceId = :serviceId")
    fun observeByServiceId(serviceId: Int): Flow<BookingEntity?>

    @Query("SELECT * FROM bookings WHERE serviceId = :serviceId")
    suspend fun getByServiceId(serviceId: Int): BookingEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(bookingEntity: BookingEntity)

    @Query("DELETE FROM bookings WHERE booking_number = :bookingNumber")
    suspend fun deleteByBookingNumber(bookingNumber: String)
}