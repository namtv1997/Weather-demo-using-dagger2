package com.example.weather.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weather.data.response.WeatherCurent
import com.example.weather.data.response.WeatherResult
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TextBinding {

    companion object {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("year")
        @JvmStatic
        fun setYear(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {

            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("yyyy EEEE MMMM-dd")

                val arr = listweather?.get(0)?.localObservationDateTime.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                val splitString =result.split(" ")
                textView.text=splitString[0]

            } catch (e: java.lang.Exception) {

            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("month")
        @JvmStatic
        fun setMonth(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {

            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("yyyy EEEE MMMM-dd")

                val arr = listweather?.get(0)?.localObservationDateTime.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                val splitString =result.split(" ")
                textView.text=splitString[1]

            } catch (e: java.lang.Exception) {

            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("day")
        @JvmStatic
        fun setDay(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {

            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("yyyy EEEE MMMM-dd")

                val arr = listweather?.get(0)?.localObservationDateTime.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                val splitString =result.split(" ")
                textView.text=splitString[2]

            } catch (e: java.lang.Exception) {

            }
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("degree")
        @JvmStatic
        fun setDegree(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {
            textView.text = "${listweather?.get(0)?.temperature?.metric?.value.toString()}ºC"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("windSpeed")
        @JvmStatic
        fun setWindSpeed(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {
            textView.text = "${listweather?.get(0)?.wind?.speed?.metric?.value.toString()}km/h"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("humidity")
        @JvmStatic
        fun setHumidity(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {
            textView.text = "${listweather?.get(0)?.relativeHumidity.toString()}%"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("tempDayOne")
        @JvmStatic
        fun setTempDayOne(textView: TextView, weatherResult: WeatherResult? = null) {
            val result =
                weatherResult?.DailyForecasts?.get(1)?.temperature?.minimum?.value?.minus(1)
                    ?.times(5)?.div(9)?.roundToInt()
            textView.text = "${result}ºC"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("tempDayTwo")
        @JvmStatic
        fun setTempDayTwo(textView: TextView, weatherResult: WeatherResult? = null) {
            val result =
                weatherResult?.DailyForecasts?.get(2)?.temperature?.minimum?.value?.minus(1)
                    ?.times(5)?.div(9)?.roundToInt()
            textView.text = "${result}ºC"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("tempDayThree")
        @JvmStatic
        fun setTempDayThree(textView: TextView, weatherResult: WeatherResult? = null) {
            val result =
                weatherResult?.DailyForecasts?.get(3)?.temperature?.minimum?.value?.minus(1)
                    ?.times(5)?.div(9)?.roundToInt()
            textView.text = "${result}ºC"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("tempDayFour")
        @JvmStatic
        fun setTempDayFour(textView: TextView, weatherResult: WeatherResult? = null) {
            val result =
                weatherResult?.DailyForecasts?.get(4)?.temperature?.minimum?.value?.minus(1)
                    ?.times(5)?.div(9)?.roundToInt()
            textView.text = "${result}ºC"
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("dayOne")
        @JvmStatic
        fun setDayOne(textView: TextView, weatherResult: WeatherResult? = null) {
            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("EEE")
                val arr = weatherResult?.DailyForecasts?.get(1)?.date.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                textView.text = result.toString()
            } catch (e: Exception) {

            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("dayTwo")
        @JvmStatic
        fun setDayTwo(textView: TextView, weatherResult: WeatherResult? = null) {
            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("EEE")
                val arr = weatherResult?.DailyForecasts?.get(2)?.date.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                textView.text = result.toString()
            } catch (e: Exception) {

            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("dayThree")
        @JvmStatic
        fun setDayThree(textView: TextView, weatherResult: WeatherResult? = null) {
            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("EEE")
                val arr = weatherResult?.DailyForecasts?.get(3)?.date.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                textView.text = result.toString()
            } catch (e: Exception) {

            }
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("dayFour")
        @JvmStatic
        fun setDayFour(textView: TextView, weatherResult: WeatherResult? = null) {
            try {
                val sdf5days = SimpleDateFormat("yyyy-MM-dd")
                val sdfCustom = SimpleDateFormat("EEE")
                val arr = weatherResult?.DailyForecasts?.get(4)?.date.toString().split(":")
                val arrResult = arr[0].split("T")
                val result = sdfCustom.format(sdf5days.parse(arrResult[0]))
                textView.text = result.toString()
            } catch (e: Exception) {

            }

        }
    }

}