package me.dio.busca.dados.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dio.busca.R
import me.dio.busca.dados.HoraPrevista
import me.dio.busca.dados.Linha
import me.dio.busca.dados.ui.adapter.HoraChegadaAdapter
import me.dio.busca.dados.ui.adapter.LinhaAdapter
import me.dio.busca.databinding.ActivityDetailPrevicaoDeChegadaBinding

class Detail_Previcao_De_Chegada : AppCompatActivity() {

    lateinit var horaprevista: ArrayList<HoraPrevista>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailPrevicaoDeChegadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        horaprevista = intent.getParcelableArrayListExtra("hora")!!
        if(::horaprevista.isInitialized)
            binding.rv.apply {
                adapter = HoraChegadaAdapter(horaprevista.filter {
                    it.t!= null
                } as ArrayList<HoraPrevista>)
            }

    }
}