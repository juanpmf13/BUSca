package me.dio.busca.dados.servicos

import androidx.room.Database
import androidx.room.RoomDatabase
import me.dio.busca.dados.Veiculo

@Database(entities = [Veiculo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
     abstract fun veiculoDao() : VeiculoDao
}