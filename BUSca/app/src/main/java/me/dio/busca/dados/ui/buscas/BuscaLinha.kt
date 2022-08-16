package me.dio.busca.dados.ui.buscas

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import me.dio.busca.MainActivity
import me.dio.busca.dados.Linha
import me.dio.busca.dados.Parada
import me.dio.busca.dados.servicos.EndPoint
import me.dio.busca.dados.ui.DetailLinha
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscaLinha: ContextThemeWrapper() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context


        fun setContext(context: Context){
            this.context= context
        }

        fun buscaLinhaPorCodigo(linha: String, service: EndPoint) {
            val response = service.getLinhaPorCodigo(linha)


            response.enqueue(object : Callback<ArrayList<Linha>> {

                override fun onFailure(call: Call<ArrayList<Linha>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<ArrayList<Linha>>, response: Response<ArrayList<Linha>>) {
                    response.body()
                    if (response.body() != null) {
                        val detaillinha = Intent(context, DetailLinha()::class.java)
                        detaillinha.putParcelableArrayListExtra("linha", response.body()!!)
                        (context).startActivity(detaillinha)
                    }
                }
            })
        }

    }

}
