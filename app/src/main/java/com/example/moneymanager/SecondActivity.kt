package com.example.moneymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.example.moneymanager.model.Tran
import com.example.moneymanager.viewmodel.TranViewModel
import kotlinx.android.synthetic.main.activity_second.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SecondActivity : AppCompatActivity() {
    private lateinit var mViewModel :TranViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Transaction"
        setContentView(R.layout.activity_second)

        mViewModel = ViewModelProvider(this).get(TranViewModel::class.java)

        submit_bt_id.setOnClickListener {

            val selectId = radio_group_id.checkedRadioButtonId
            val radioBt = findViewById<RadioButton>(selectId)
            val typeString = radioBt.text
            var type = 'c'
            if(typeString == "Debit"){
                type = 'd'
            }

            val amount  = amount_id.text.toString()
            val desc = desc_id.text.toString()

            if(amount.isNotEmpty()){
                val amountInteger = amount.toInt()
                if(desc.isEmpty()){
                    val tran = Tran(0,amountInteger,type,"No_Description",currentDate())
                    mViewModel.addTransaction(tran)
                }
                else{
                    val tran = Tran(0,amountInteger,type,desc,currentDate())
                    mViewModel.addTransaction(tran)
                }
            }

            finish()

        }
    }
    private fun currentDate():String{
        val date = LocalDateTime.now()
        val formatter  = DateTimeFormatter.ofPattern("HH:mm -- dd/MM/yy")
        return date.format(formatter).toString()
    }

}