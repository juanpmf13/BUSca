package me.dio.busca

import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.dio.busca.dados.servicos.EndPoint
import me.dio.busca.dados.servicos.Network
import me.dio.busca.dados.ui.buscas.BuscaLinha
import me.dio.busca.dados.ui.buscas.BuscaParada
import me.dio.busca.dados.ui.buscas.BuscaTempoPorParada
import me.dio.busca.dados.ui.buscas.BuscaVeiculo
import me.dio.busca.dados.ui.map.MapsActivity
import me.dio.busca.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var   service : EndPoint
    val retrofite by lazy { Network }
    var orientacao : Int? = null

    /*
        -- possibilidade de orientação
        1-> Busca para por Termo
        2-> Busca para por Corredor
        3-> Busca para por Linha


    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        BuscaParada.setContext(this)
        BuscaLinha.setContext(this)
        BuscaVeiculo.setContext(this)
        BuscaTempoPorParada.setContext(this)
        service = retrofite.service()

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.getPost()
            if (response.isSuccessful) {
                val am: AccountManager = AccountManager.get(this@MainActivity)
                Log.i("resultado", am.toString())
                click()
            } else {
                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }


    }
    fun click( ) {

        binding.chip1.setOnClickListener {
            garanteInvi()
            binding.rgParada.isVisible= true
            posibilidade1()
        }
        binding.chip2.setOnClickListener {
            garanteInvi()
            orientacao = 7
            posibilidade1()
        }

        binding.chip3.setOnClickListener {
            garanteInvi()
            orientacao = 4
            posibilidade1()
        }
        binding.chip4.setOnClickListener {
            garanteInvi()
            binding.rgVeiculo.isVisible=true
            posibilidade1()
        }
        binding.btnConfirm.setOnClickListener {
            buscaParada()
            when(orientacao){
                1-> BuscaParada.buscaParadaPorTermo(binding.entrada1.getText().toString(),service)
                2-> BuscaParada.buscaParadaPorCorredor(binding.entrada1.getText().toString().toInt(),service)
                3-> BuscaParada.buscaParadaPorLinha(binding.entrada1.getText().toString().toInt(),service)
                4-> BuscaLinha.buscaLinhaPorCodigo(binding.entrada1.getText().toString(),service)
                5-> CoroutineScope(Dispatchers.IO).launch{  BuscaVeiculo.buscaTodosOsVeiculo(service)}
                6-> CoroutineScope(Dispatchers.IO).launch{  BuscaVeiculo.buscaVeiculoPorLinha(binding.entrada1.getText().toString().toInt(),service)}
                7-> BuscaTempoPorParada.buscaHoraDeChegada(binding.entrada1.getText().toString().toInt(), service)
            }
            garanteInvi()
        }

    }

    fun posibilidade1() {
        binding.entrada1.isVisible = true
        binding.btnConfirm.isVisible = true
    }



    fun buscaParada() {
        if (binding.rbParadaPorTermo.isChecked) {
            orientacao = 1
        }else
            if (binding.rbParadaPorCorredor.isChecked) {
                orientacao = 2
            }else
                if (binding.rbParadaPorLinha.isChecked) {
                    orientacao = 3
                }
        if (binding.rbVeiculoTodos.isChecked){
            orientacao =5
        }else{

            if (binding.rbVeiculoLinha.isChecked){
                orientacao =6
            }
        }
    }

    private fun iniciaMapa() {
        val maps = Intent(this, MapsActivity::class.java)
        startActivity(maps)


    }


    fun garanteInvi(){
        binding.rgParada.isVisible=false
        binding.rgVeiculo.isVisible=false
        binding.entrada1.isVisible=false
        binding.entrada2.isVisible=false
        binding.btnConfirm.isVisible=false
        binding.rgParada.clearCheck()
        binding.rgVeiculo.clearCheck()
    }

}







