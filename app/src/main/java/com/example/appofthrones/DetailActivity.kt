package com.example.appofthrones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast



class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val button: Button = findViewById(R.id.button)

    }

    fun showDetails(button: View){
            val intent= Intent(this, CharactersActivity::class.java)
          startActivity(intent)


    }

}
