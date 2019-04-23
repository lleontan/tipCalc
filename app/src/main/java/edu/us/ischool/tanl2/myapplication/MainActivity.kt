package edu.us.ischool.tanl2.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    var cents=0;
    val charReg = Regex("[$.]")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val amountText = findViewById<TextView>(R.id.amount_text) as TextView
        val billInput = findViewById<TextView>(R.id.bill_input) as EditText
        val submitButton = findViewById<TextView>(R.id.submit_button) as Button
        submitButton.setOnClickListener {
            val newTipDec:Int=(cents*.15).toInt()%100
            val newTipInt:Int=(cents*.15).toInt()/100
            Toast.makeText(this, "$"+newTipInt+"."+newTipDec, Toast.LENGTH_SHORT).show()
        }
        billInput.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable) {
                billInput.removeTextChangedListener(this)
                if (getCurrentFocus() == billInput) {
                    billInput.clearFocus()
                    submitButton.isClickable=true
                    var newAmount = s.replace(charReg, "")
                    //billInput.setText(newAmount)
                    try {
                        cents = (newAmount.toDouble()).toInt()
                        val newMoneyDec:Int=cents%100
                        val newMoneyInt:Int=cents/100
                        //Log.v("tipCalc", "ThingChanged")

                        billInput.setText("$"+newMoneyInt+"."+newMoneyDec)
                    } catch (ex: NumberFormatException) {
                        Log.e("tipCalc", "Not a valid number")
                    }
                }
                billInput.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })
        submitButton.isClickable=false
    }
}
