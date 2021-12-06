package com.example.calllogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calllogs.database.MySqliteDB
import com.example.calllogs.databinding.ActivityMainBinding
import com.example.calllogs.models.Call

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DatabaseUtility.db = MySqliteDB(this)
        binding.button.setOnClickListener {
            val name = binding.editTextTextPersonName.text.toString()
            val number = binding.editTextPhone.text.toString()
            val type = binding.spinner.selectedItem.toString()
            val call = Call(type, name, number)
            if (DatabaseUtility.db!!.addCall(call)) {
                Toast.makeText(this, "تمت الاضافة بنجاح", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnLogs.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CallLogs::class.java
                )
            )
        }
    }
}