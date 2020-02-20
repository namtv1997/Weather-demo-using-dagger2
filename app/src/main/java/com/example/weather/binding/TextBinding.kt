package com.example.weather.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.remote.pojo.response.WeatherResult
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class TextBinding {

    companion object {

        @SuppressLint("SetTextI18n")
        @BindingAdapter("year")
        @JvmStatic
        fun setYear(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {
            //  textView.text = customDateTime(listweather?.get(0)?.localObservationDateTime.toString())[0]
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("month")
        @JvmStatic
        fun setMonth(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {
            //  textView.text = customDateTime(listweather?.get(0)?.localObservationDateTime.toString())[1]
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("day")
        @JvmStatic
        fun setDay(textView: TextView, listweather: ArrayList<WeatherCurent>? = null) {
            //  textView.text = customDateTime(listweather?.get(0)?.localObservationDateTime.toString())[2]
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
        @BindingAdapter("tempDayThee")
        @JvmStatic
        fun setTempDayThee(textView: TextView, weatherResult: WeatherResult? = null) {
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

        @SuppressLint("SetTextI18n")
        @BindingAdapter("dayOne")
        @JvmStatic
        fun setdDayOne(textView: TextView, weatherResult: WeatherResult? = null) {
            //   textView.text = customDay(weatherResult?.DailyForecasts?.get(1)?.date.toString())
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("dayTwo")
        @JvmStatic
        fun setdDayTwo(textView: TextView, weatherResult: WeatherResult? = null) {
            // textView.text = customDay(weatherResult?.DailyForecasts?.get(2)?.date.toString())
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("dayThree")
        @JvmStatic
        fun setDayThree(textView: TextView, weatherResult: WeatherResult? = null) {
            //   textView.text = customDay(weatherResult?.DailyForecasts?.get(3)?.date.toString())
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        @BindingAdapter("dayFour")
        @JvmStatic
        fun setDayFour(textView: TextView, weatherResult: WeatherResult? = null) {
//            //  textView.text = customDay(weatherResult?.DailyForecasts?.get(4)?.date.toString())
//
//            val sdf5days = SimpleDateFormat("yyyy-MM-dd")
//            val s = weatherResult?.DailyForecasts?.get(4)?.date.toString()
//            val arr = s.split(":")
//            val a=arr[0]
//            val sdfday = SimpleDateFormat("EEE")
//            val arr1 = a.split("T")
//            val b=arr1[0]
//            val c=sdf5days.parse(b)
//            textView.text = c.toString()

        }

        @SuppressLint("SimpleDateFormat")
        fun customDateTime(dateTime: String? = null): List<String> {
            val sdfCurrent = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val shortdateInStringCurrent = dateTime?.substring(0, 19)
            val date = sdfCurrent.parse(shortdateInStringCurrent.toString())
            val sdfCurrentDate = SimpleDateFormat("yyyy EEEE MMMM-dd")
            val splitString = " "
            val part = sdfCurrentDate.format(date).split(splitString)
            return part
        }

        @SuppressLint("SimpleDateFormat")
        fun customDay(dateTime: String? = null): String {
            val sdf5days = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val shortdateInStringDay = dateTime?.substring(0, 19)
            val sdf5days1 = SimpleDateFormat("EEE")
            val day = sdf5days.parse(shortdateInStringDay.toString())
            return sdf5days1.format(day)
        }

    }

}