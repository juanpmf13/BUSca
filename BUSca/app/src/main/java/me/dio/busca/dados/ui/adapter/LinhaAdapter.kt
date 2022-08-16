package me.dio.busca .dados.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.dio.busca.dados.Linha
import me.dio.busca.dados.ui.DetailLinha
import me.dio.busca.databinding.ActivityDetailLinhaBinding
import me.dio.busca.databinding.CardLinhaBinding
import org.w3c.dom.Text

class LinhaAdapter(val lin : ArrayList<Linha>):RecyclerView.Adapter<LinhaAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return lin.size
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int ){
        viewHolder.onLinBind(lin[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder{
        return ViewHolder(CardLinhaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ViewHolder(private val binding: CardLinhaBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onLinBind(linha: Linha, ){
            binding.tvLetreiro.setText(linha.lt+linha.tl.toString())
            binding.tvCodigo.setText(linha.cl.toString())
            if (linha.lc == true){
                binding.x.invalidate()
                binding.tvT1.setText(linha.tp)
                binding.tvT2.setText("Circular")
            }else{
                if(linha.sl?.toInt()  == 1){
                    binding.tvT1.setText(linha.tp)
                    binding.tvT2.setText(linha.ts)
                }
                else{
                    binding.tvT1.setText(linha.ts)
                    binding.tvT2.setText(linha.tp)
                }
            }
        }
    }
}