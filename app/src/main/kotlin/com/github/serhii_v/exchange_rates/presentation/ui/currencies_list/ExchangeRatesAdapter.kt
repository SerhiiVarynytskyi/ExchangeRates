package com.github.serhii_v.exchange_rates.presentation.ui.currencies_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.serhii_v.exchange_rates.databinding.RowCurrencyBinding
import com.github.serhii_v.exchange_rates.domain.entity.Entity

class ExchangeRatesAdapter(
    private val baseCurrencyName: String,
    private val rates: List<Entity.Rate>,
    private val listener: ((baseCurrency: String, Entity.Rate) -> Unit)?
) : RecyclerView.Adapter<ExchangeRatesAdapter.RowViewHolder>() {

    override fun getItemCount(): Int {
        return rates.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowCurrencyBinding.inflate(layoutInflater, parent, false)
        return RowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.bind(baseCurrencyName, rates[position], listener)
    }

    class RowViewHolder(private var binding: RowCurrencyBinding) : ViewHolder(binding.root) {
        fun bind(baseCurrency: String, rate: Entity.Rate, listener: ((baseCurrency: String, Entity.Rate) -> Unit)?) {
            binding.rate = rate
            binding.root.setOnClickListener { listener?.invoke(baseCurrency, rate) }
            binding.executePendingBindings()
        }
    }

}