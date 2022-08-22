package me.dio.busca.dados.ui.buscas

import android.content.Context
import android.content.Intent
import android.widget.Toast
import me.dio.busca.dados.HoraPrevista
import me.dio.busca.dados.Parada
import me.dio.busca.dados.VeiculoHora
import me.dio.busca.dados.servicos.EndPoint
import me.dio.busca.dados.ui.detail.Detail_Previcao_De_Chegada
import me.dio.busca.dados.ui.map.MapsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaTempoPorParada {

    companion object {
        private lateinit var context: Context

        fun setContext(context: Context) {
            this.context = context
        }

        fun buscaHoraDeChegada(linha: Int, service: EndPoint) {
            val response = service.getHorariodeChegadaPorParada(linha)

            response.enqueue(object : Callback<VeiculoHora> {

                override fun onFailure(call: Call<VeiculoHora>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<VeiculoHora>,
                    response: Response<VeiculoHora>
                ) {
                    response.body()
                    if (response.body() != null) {
                        val intent = Intent(context, Detail_Previcao_De_Chegada::class.java)
                        var valor = ArrayList<HoraPrevista>()
                        val nome = response.body()!!.p.np
                        response.body()!!.p.l.forEach { l ->
                            l.vs.forEach { v ->
                                valor?.add(HoraPrevista(nome,l.c,l.lt0,l.lt1,l.sl.toInt(),v.t))
                            }
                        }
                        intent.putParcelableArrayListExtra("hora",valor)
                        context.startActivity(intent)
                    }
                }
            })
        }
    }
}