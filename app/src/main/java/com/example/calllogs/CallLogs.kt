package com.example.calllogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calllogs.adapter.RecAdapter
import com.example.calllogs.database.MySqliteDB
import com.example.calllogs.databinding.ActivityCallLogsBinding
import com.example.calllogs.models.Call

class CallLogs : AppCompatActivity() {
    lateinit var binding : ActivityCallLogsBinding
    lateinit var db : MySqliteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MySqliteDB(this)
        binding = ActivityCallLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.adapter = RecAdapter(db.getAllCalls())
        binding.list.layoutManager = LinearLayoutManager(this)
    }
}