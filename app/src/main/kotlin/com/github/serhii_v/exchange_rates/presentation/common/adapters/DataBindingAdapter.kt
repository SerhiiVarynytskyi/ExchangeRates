package com.github.serhii_v.exchange_rates.presentation.common.adapters

import android.R
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.domain.entity.Entity
import com.github.serhii_v.exchange_rates.domain.entity.Entity.CurrencyDetails
import com.github.serhii_v.exchange_rates.presentation.common.extension.toMoneyValue
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.ColumnChartView
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("android:adapter")
fun setAdapter(recyclerView: RecyclerView, adapter: MutableLiveData<RecyclerView.Adapter<*>>?) {
    recyclerView.adapter = adapter?.value
}


@BindingAdapter("uiState")
fun setUiStateForLoading(progressView: ProgressBar, state: State?) {
    progressView.visibility = when (state) {
        State.Loading -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("uiState")
fun setUiStateForLoadedContent(view: View, state: State) {
    view.visibility = when (state) {
        State.HasData -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("emptyState")
fun setUiStateForEmptyView(view: View, state: State) {
    view.visibility = when (state) {
        State.NoData -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("errorState")
fun setUiStateForErrorView(view: View, state: State) {
    view.visibility = when (state) {
        is Error -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("data")
fun setDate(spinner: AppCompatSpinner, currencyLiveData: MutableLiveData<Entity.Currency>?) {
    currencyLiveData?.value?.let { currency->
        val rates = currency.rates
        val list = rates.map { it.currencyName }
        val adapter = ArrayAdapter<String>(spinner.context, R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(list.indexOf(currency.currencyName))
    }
}

@BindingAdapter("data")
fun setDate(chart: ColumnChartView, currencyDetails: MutableLiveData<CurrencyDetails>?) {
    val columns: MutableList<Column> = ArrayList()
    val axisValues: MutableList<AxisValue> = ArrayList()

    currencyDetails?.value?.rates?.sortedBy { it.date }?.let {
        val format = SimpleDateFormat("dd.MM", Locale.US)
        for (index in it.indices) {
            val columnValue = SubcolumnValue(it[index].currencyValue, ChartUtils.COLOR_BLUE)
            columnValue.setLabel(it[index].currencyValue.toMoneyValue())
            columns.add(Column(arrayListOf(columnValue)).setHasLabels(true))
            val date = it[index].date
            axisValues.add(AxisValue(index.toFloat()).setLabel(if (date != null) format.format(date) else ""))
        }
    }

    val data = ColumnChartData(columns)
    data.axisXBottom = Axis(axisValues)
    data.axisYLeft = Axis().setHasLines(true).setMaxLabelChars(4).setName("Price")
    chart.columnChartData = data

    val v = Viewport(chart.maximumViewport)
    val dy = v.height() * 0.2f
    v.set(v.left, v.top + dy, v.right, v.bottom)
    chart.maximumViewport = v
    chart.currentViewport = v
}
