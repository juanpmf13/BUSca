package me.dio.busca.dados.ui.buscas

import android.content.Context
import android.content.Intent
import android.widget.Toast
import me.dio.busca.dados.Veiculo
import me.dio.busca.dados.servicos.EndPoint
import me.dio.busca.dados.ui.map.MapsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaVeiculo {
    companion object{
        private lateinit var context: Context

        fun setContext(context: Context){
            this.context= context
        }

        fun buscaTodosOsVeiculo( service: EndPoint) {
            val response = service.getTodosOsVeiculos()

            response.enqueue(object : Callback<Veiculo> {

                override fun onFailure(call: Call<Veiculo>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<Veiculo>, response: Response<Veiculo>) {
                    response.body()
                    if (response.body() != null) {
                        val intent = Intent(context, MapsActivity::class.java)
                        intent.putExtra("veiculo", response.body())
                        context.startActivity(intent)
                    }
                }
            })
        }

        fun buscaVeiculoPorLinha(valor:Int, service: EndPoint) {
            val response = service.getVeiculoPorLinha(valor)

            response.enqueue(object : Callback<Veiculo> {

                override fun onFailure(call: Call<Veiculo>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<Veiculo>, response: Response<Veiculo>) {
                    response.body()
                    if (response.body() != null) {
                        val intent = Intent(context, MapsActivity::class.java)
                        intent.putExtra("veiculo", response.body())
                        context.startActivity(intent)
                    }
                }
            })
        }
    }

}