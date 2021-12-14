package com.example.calllogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calllogs.adapter.RecAdapter
import com.example.calllogs.database.MySqliteDB
import com.example.calllogs.databinding.ActivityCallLogsBinding
import com.example.calllogs.models.Call

class CallLogs : AppCompatActivity() {
    lateinit var binding : ActivityCallLogsBinding
    lateinit var db : MySqliteDB
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MySqliteDB(this)
        binding = ActivityCallLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.mToolbar
        setSupportActionBar(toolbar)
        binding.mToolbar.title = ""
        binding.list.adapter = RecAdapter(db.getAllCalls())
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                //(binding.list.adapter as RecAdapter).search(text)
                binding.etSearch.clearFocus()
            }
        })

        binding.etSearch2.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                (binding.list.adapter as RecAdapter).search(s.toString())
            }
        })
    }
}