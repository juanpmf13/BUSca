package me.dio.busca.dados.ui.buscas

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.dio.busca.dados.Veiculos
import me.dio.busca.dados.servicos.EndPoint
import me.dio.busca.dados.ui.map.MapsActivity
import me.dio.busca.dados.Veiculo
import me.dio.busca.dados.VeiculoLinha
import me.dio.busca.dados.servicos.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaVeiculo {
    companion object{
        private lateinit var context: Context

        fun setContext(context: Context){
            this.context= context
        }

        suspend fun buscaTodosOsVeiculo( service: EndPoint) {
            val response = service.getTodosOsVeiculos()

            response.enqueue(object : Callback<Veiculos> {

                override fun onFailure(call: Call<Veiculos>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(call: Call<Veiculos>, response: Response<Veiculos>) {
                    response.body()
                    if (response.body() != null) {

                        val intent = Intent(context, MapsActivity::class.java)
                        var contador =0
                        if (response.body() != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                val db = Room.databaseBuilder(
                                    context,
                                    AppDatabase::class.java,
                                    "veiculo"
                                )
                                    .build()
                                val veiculoDao = db.veiculoDao()
                                response.body()!!.l.forEach { l ->
                                    l.vs.forEach {
                                        veiculoDao.insereVeiculo(
                                            Veiculo(
                                                contador,
                                                it.px,
                                                it.py,
                                                l.lt0,
                                                l.lt1
                                            )
                                        )
                                        contador++
                                    }
                                }
                                intent.putExtra("codigo", 4)
                                context.startActivity(intent)
                            }

                        }

                    }

                }
            })
        }

        fun buscaVeiculoPorLinha(valor:Int, service: EndPoint) {
            val response = service.getVeiculoPorLinha(valor)

            response.enqueue(object : Callback<VeiculoLinha> {

                override fun onFailure(call: Call<VeiculoLinha>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<VeiculoLinha>, response: Response<VeiculoLinha>) {
                    response.body()
                    if (!response.body()?.vs.isNullOrEmpty()) {
                        val intent = Intent(context, MapsActivity::class.java)
                        var contador =0
                        intent.putExtra("codigo",2)
                        intent.putParcelableArrayListExtra("veiculolinha", response.body()!!.vs.toCollection(ArrayList()))
                        context.startActivity(intent)
                    }else
                        Toast.makeText(context, "nenhuma linha encontrada", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}