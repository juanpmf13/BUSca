package me.dio.busca.dados.servicos

import android.net.Uri
import me.dio.busca.dados.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EndPoint {

    @POST("Login/Autenticar?token=4d9295b0e48308da9314ddc3e56349163f6260d07539142f5bbf13fd6892e2fb")
   suspend fun getPost(): Response<ResponseBody>

    @GET("Linha/Buscar?termosBusca=8000")
    fun getLinhas(): Call<ArrayList<Linha>>

    @GET("Parada/BuscarParadasPorCorredor")
    fun getParadaPorCorredor(@Query ("codigoCorredor")codigo:Int): Call<ArrayList<Parada>>

    @GET("Parada/Buscar")
    fun getParadaPorTermo(@Query ("termosBusca")termos:String): Call<ArrayList<Parada>>


    @GET("Parada/BuscarParadasPorLinha")
    fun getParadaPorLinha(@Query ("codigoLinha")termos: Int): Call<ArrayList<Parada>>

    @Headers("Cache-Control: max-age=15724800")
    @GET("Linha/Buscar")
    fun getLinhaPorCodigo(@Query ("termosBusca")termos:String): Call<ArrayList<Linha>>

    @Headers("Cache-Control: max-age=640000")
    @GET("/Posicao")
    fun getTodosOsVeiculos(): Call<Veiculos>

    @Headers("Cache-Control: max-age=640000")
    @GET("/Posicao/Linha")
    fun getVeiculoPorLinha(@Query ("codigoLinha") valor: Int): Call<VeiculoLinha>

    @GET("/Previsao/Parada")
    fun getHorariodeChegadaPorParada(@Query ("codigoParada") valor: Int): Call<VeiculoHora>
}
