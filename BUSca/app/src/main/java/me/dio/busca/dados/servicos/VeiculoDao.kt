package me.dio.busca.dados.servicos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.dio.busca.dados.Veiculo
import me.dio.busca.dados.Veiculos

@Dao
interface VeiculoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insereVeiculo(vararg veiculos: Veiculo)

    @Query("SELECT * FROM veiculo")
     fun carregaVeiculo(): List<Veiculo>
}