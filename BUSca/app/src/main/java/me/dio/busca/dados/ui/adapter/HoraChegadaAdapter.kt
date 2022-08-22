package me.dio.busca.dados.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.dio.busca.dados.HoraPrevista
import me.dio.busca.databinding.CardHoraBinding

class HoraChegadaAdapter (val hora : ArrayList<HoraPrevista>):RecyclerView.Adapter<HoraChegadaAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return hora.size
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int ){
        viewHolder.horaBind(hora[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder{
        return ViewHolder(CardHoraBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ViewHolder(private val binding: CardHoraBinding): RecyclerView.ViewHolder(binding.root){

        fun horaBind(hora: HoraPrevista){
            binding.tvLetreiroDoOnibus.setText(hora.c)
            binding.tvHorarioprevisto.setText(hora.t.toString())
            binding.tvNomeDaParada.setText(hora.np)

            if (hora.sl == 0){
                binding.x.invalidate()
                binding.tvVinda.setText(hora.lt0.toString())
                binding.tvIda.setText(hora.lt1.toString())
            }else{
                    binding.tvIda.setText(hora.lt1.toString())
                    binding.tvVinda.setText(hora.lt0.toString())

            }
        }
    }
}