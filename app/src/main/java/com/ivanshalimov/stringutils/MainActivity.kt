package com.ivanshalimov.stringutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ivanshalimov.stringutils.StringUtils.isAlphanumeric

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Test","${"#@$%".isAlphanumeric}")
    }
}