package com.felina.waterqualitycontrol

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felina.waterqualitycontrol.domain.model.WaterQualityData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.publish
import kotlinx.coroutines.flow.updateAndGet
import kotlin.math.log

class MainViewModel : ViewModel() {
    private val first : WaterQualityData = WaterQualityData(temp = 25,
    ph = 8.6,
    oxigen = 9,
    tds = 332,
    salinity = 31.1,
    amonia = 0.02)
    private var _Data = MutableLiveData(first)
    val Data : LiveData<WaterQualityData> = _Data
    suspend fun getRealTimeData() {
        var current  = WaterQualityData(temp = 20,
            ph = 7.6,
            oxigen = 6,
            tds = 335,
            salinity = 34.5,
            amonia = 0.04)
        while (true) {
            current.temp--
            current.ph++
            current.oxigen++
            current.tds--
            current.amonia = 0.03
            _Data.value = current
            Log.e("pertama",_Data.value.toString())
            delay(4000)

            current.temp++
            current.ph--
            current.oxigen--
            current.salinity++
            _Data.value = current

            Log.e("kedua",_Data.value.toString())
            delay(4000)

            current.temp++
            current.ph++
            current.oxigen--
            current.salinity--
            _Data.value = current

            Log.e("ketiga",_Data.value.toString())
            delay(4000)

            current.temp--
            current.ph--
            current.oxigen++
            current.tds++
            current.amonia = 0.04
            _Data.value = current

            Log.e("keempat",_Data.value.toString())
            delay(4000)
        }
    }
}