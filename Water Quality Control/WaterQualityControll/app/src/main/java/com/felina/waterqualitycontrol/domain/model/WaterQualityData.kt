package com.felina.waterqualitycontrol.domain.model

data class WaterQualityData(
    var temp: Int,
    var ph: Double,
    var oxigen: Int,
    var tds: Int,
    var salinity: Double,
    var amonia: Double

)
