package com.example.todoapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todoapp.databinding.ActivityCategoryBinding

class ActivityCategory : AppCompatActivity() {
    private lateinit var binding:ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setContentView(R.layout.activity_category)
     intent.getStringExtra("Data").run{
          Log.d(TAG,"oncreate :$this" )
       }

initViews()
    }
    private fun initViews(){
        binding.btnSaveCategory.setOnClickListener {
            binding.etNewCategory.text?.toString()?.run{
                if(this.isNotEmpty()){
                    sendDataBack(this)
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
      Toast.makeText(this,"no empty category",Toast.LENGTH_LONG).show()
    }

    private fun sendDataBack(newCategory: String) {
       val sendDataIntent: Intent = Intent()
        sendDataIntent.putExtra("NewCateGory",newCategory)
        setResult(RESULT_OK,sendDataIntent)
        // To terminate current activity

        finish()

    }
}