import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Database
import androidx.room.RoomDatabase

// ==========================================
// 1. TABLAS DE LA BASE DE DATOS
// ==========================================

// Tabla para el Botón de Emergencia
@Entity(tableName = "contactos_emergencia")
data class ContactoEmergencia(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val telefono: String,
    val relacion: String? = null
)

// Tabla para el Historial del Asistente Virtual
@Entity(tableName = "historial_asistente")
data class HistorialAsistente(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val peticionUsuario: String,
    val respuestaIA: String,
    val timestamp: Long = 0L
)

// ==========================================
// 2. DAOs (Consultas para insertar, leer y borrar)
// ==========================================

@Dao
interface ContactoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarContacto(contacto: ContactoEmergencia)

    @Delete
    suspend fun eliminarContacto(contacto: ContactoEmergencia)

    @Query("SELECT * FROM contactos_emergencia")
    suspend fun obtenerTodosLosContactos(): List<ContactoEmergencia>
}

@Dao
interface AsistenteDao {
    @Insert
    suspend fun guardarInteraccion(historial: HistorialAsistente)

    @Query("SELECT * FROM historial_asistente ORDER BY timestamp DESC LIMIT 10")
    suspend fun obtenerHistorialReciente(): List<HistorialAsistente>
}

// ==========================================
// 3. CONTROLADOR CENTRAL DE LA BASE DE DATOS
// ==========================================

@Database(entities = [ContactoEmergencia::class, HistorialAsistente::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactoDao(): ContactoDao
    abstract fun asistenteDao(): AsistenteDao
}