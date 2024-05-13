package com.berktavli.cryptoretrofit.ui.adapter

import android.graphics.Color
import android.location.GnssAntennaInfo
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berktavli.cryptoretrofit.data.entity.CryptoModel
import com.berktavli.cryptoretrofit.databinding.CryptoRowLayoutBinding

class CryptoAdapter(private  val cryptoList : ArrayList<CryptoModel>, private val listener: Listener) : RecyclerView.Adapter<CryptoAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors : Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")
    inner class RowHolder(var vh : CryptoRowLayoutBinding) : RecyclerView.ViewHolder(vh.root){
        fun bind (cryptoModel: CryptoModel, colors : Array<String>, position: Int, listener : Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_row_layout,parent,false)
        //return RowHolder(view)
        val binding = CryptoRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }
    override fun getItemCount(): Int {
        return cryptoList.size
    }
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val queryCrypto = cryptoList.get(position)
        val _vh = holder.vh
        _vh.textName.text = queryCrypto.currency
        _vh.textPrice.text = queryCrypto.price
        holder.bind(cryptoList[position],colors,position,listener)
    }
}