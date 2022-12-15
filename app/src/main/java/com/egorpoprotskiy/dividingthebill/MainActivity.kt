package com.egorpoprotskiy.dividingthebill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Transformations
import com.egorpoprotskiy.dividingthebill.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        //вписываем сумму чека и перевод ее в строку
        val summaText = binding.enterSummaEdit.text.toString()
        //переводит summaText в Double или в null
        var summa = summaText.toDoubleOrNull()
        // Возвращает десятичное число, если может, или возвращает null, если есть проблема.
        if (summa == null || summa == 0.0){
            binding.totalPerPersonEdit.setText(null)
            return
        }
        val peoplesText = binding.enterPeoplesEdit.text.toString()
        val peoples = peoplesText.toDoubleOrNull()
        if (peoples == null || peoples == 0.0){
            binding.totalPerPersonEdit.setText(null)
            return
        }
        //значение чаевых в зависимости от выбранного пункта
        val tip: Double = when (binding.change.checkedRadioButtonId) {
            R.id.change10 -> 0.1
            R.id.change15 -> 0.15
            R.id.change20 -> 0.2
            else -> 0.0
        }
        //итоговый расчёт с учетом чаевых и количества человек
        val result = (summa + (summa * tip)) / peoples
        //отображение в местной валюте
        val formatted = NumberFormat.getCurrencyInstance().format(result)
        binding.totalPerPersonEdit.setText("$formatted")
    }
}