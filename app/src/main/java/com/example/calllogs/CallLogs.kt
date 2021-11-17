package com.example.calllogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calllogs.databinding.ActivityCallLogsBinding
import com.example.calllogs.models.Call

class CallLogs : AppCompatActivity() {
    lateinit var binding : ActivityCallLogsBinding
    companion object{
        val lisOfCalls = arrayListOf<Call>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listView.adapter = CallsAdapter(lisOfCalls)
    }
}