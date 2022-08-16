package me.dio.busca.dados.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import me.dio.busca.dados.Linha
import me.dio.busca.dados.ui.adapter.LinhaAdapter
import me.dio.busca.databinding.ActivityDetailLinhaBinding
import me.dio.busca.databinding.ActivityMainBinding

class DetailLinha() : AppCompatActivity() {

    lateinit var lin: ArrayList<Linha>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailLinhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lin = intent.getParcelableArrayListExtra("linha")!!
        if (::lin.isInitialized)
        binding.rv.apply {
            adapter = LinhaAdapter(lin.filter {
                it.tp!= null
            } as ArrayList<Linha>)
        }


    }
}