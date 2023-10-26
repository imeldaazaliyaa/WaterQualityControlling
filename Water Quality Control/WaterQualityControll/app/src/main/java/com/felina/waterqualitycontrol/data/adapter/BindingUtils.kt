package com.felina.waterqualitycontrol.data.adapter

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.felina.waterqualitycontrol.R

fun CardView.setTempDangerType(range: String) {
    when (range.toInt()){
        in 0..19 -> {
            setCardBackgroundColor(resources.getColor(R.color.warn, resources.newTheme()))
        }
        in 20..30 -> {
            setCardBackgroundColor(resources.getColor(R.color.safe, resources.newTheme()))
        }
        in 31..50 -> {
            setCardBackgroundColor(resources.getColor(R.color.danger, resources.newTheme()))
        }
    }
}
fun CardView.setPhDangerType(range: String) {
    when (range.toFloat()){
        in 0.0..7.4 -> {
            setCardBackgroundColor(resources.getColor(R.color.warn, resources.newTheme()))
        }
        in 7.5..8.8 -> {
            setCardBackgroundColor(resources.getColor(R.color.safe, resources.newTheme()))
        }
        in 8.9..50.0 -> {
            setCardBackgroundColor(resources.getColor(R.color.danger, resources.newTheme()))
        }
    }
}
fun CardView.setOxigenDangerType(range: String) {
    when (range.toInt()){
        in 0..3 -> {
            setCardBackgroundColor(resources.getColor(R.color.warn, resources.newTheme()))
        }
        in 4..6 -> {
            setCardBackgroundColor(resources.getColor(R.color.safe, resources.newTheme()))
        }
        in 6..50 -> {
            setCardBackgroundColor(resources.getColor(R.color.danger, resources.newTheme()))
        }
    }
}
fun CardView.setTdsDangerType(range: String) {
    when (range.toInt()){
        in 0..299 -> {
            setCardBackgroundColor(resources.getColor(R.color.warn, resources.newTheme()))
        }
        in 300..600 -> {
            setCardBackgroundColor(resources.getColor(R.color.safe, resources.newTheme()))
        }
        in 601..1000 -> {
            setCardBackgroundColor(resources.getColor(R.color.danger, resources.newTheme()))
        }
    }
}
fun CardView.setSalinityDangerType(range: String) {
    when (range.toFloat()) {
        in 0.0..0.4 -> {
            setCardBackgroundColor(resources.getColor(R.color.warn, resources.newTheme()))
        }

        in 0.5..45.0 -> {
            setCardBackgroundColor(resources.getColor(R.color.safe, resources.newTheme()))
        }

        in 46.0..100.0 -> {
            setCardBackgroundColor(resources.getColor(R.color.danger, resources.newTheme()))
        }
    }
}
fun CardView.setAmoniaDangerType(range: String) {
        when (range.toFloat()){
            in 0.0..0.4 -> {
                setCardBackgroundColor(resources.getColor(R.color.safe, resources.newTheme()))
            }
            in 0.5..100.0 -> {
                setCardBackgroundColor(resources.getColor(R.color.danger, resources.newTheme()))
            }
        }
}
fun TextView.setDataUpdate(data: String, type: String) {
    when (type) {
        "temp" -> {
            text = resources.getString(R.string.temp_data, data)
        }
        "ph" -> {
            text = resources.getString(R.string.ph_data, data)
        }
        "oxigen" -> {
            text = resources.getString(R.string.oxigen_data, data)
        }
        "tds" -> {
            text = resources.getString(R.string.tds_data, data)
        }
        "salinity" -> {
            text = resources.getString(R.string.salinity_data, data)
        }
        "amonia" -> {
            text = resources.getString(R.string.amonia_data, data)
        }
    }
}