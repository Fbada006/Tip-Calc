package com.example.tipcalc

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var etBill: EditText
    private lateinit var etTip: EditText
    private lateinit var tvBill: TextView
    private lateinit var tvTip: TextView
    private var bill = 0.00
    private var tip = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBill = findViewById(R.id.et_bill)
        etTip = findViewById(R.id.et_tip)
        tvBill = findViewById(R.id.tv_bill)
        tvTip = findViewById(R.id.tv_tip)

        calculateTip()
    }

    private fun calculateTip() {
        tip = etTip.text.toString().toDouble()
        val initialTip = (tip / 100 * bill).toString()
        tvTip.text = getString(R.string.tip, initialTip)
        val initialBill = (bill + (tip / 100 * bill)).toString()
        tvBill.text = getString(R.string.bill, initialBill)

        etBill.doAfterTextChanged { editable ->
            try {
                bill = editable.toString().toDouble()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            if ((bill > 0.00) && (tip > 0.00)) {
                val bill = (bill + (tip / 100 * bill)).toString()
                tvBill.text = getString(R.string.bill, bill)
            }
            tvTip.text = getString(R.string.tip, (tip / 100 * bill).toString())
        }

        etTip.doAfterTextChanged { editable ->
            try {
                tip = editable.toString().toDouble()
            } catch (e: java.lang.NumberFormatException) {
                e.printStackTrace()
            }
            if (bill > 0.00) {
                val bill = (bill + (tip / 100 * bill)).toString()
                tvBill.text = getString(R.string.bill, bill)
            }
            tvTip.text = getString(R.string.tip, (tip / 100 * bill).toString())
        }
    }
}