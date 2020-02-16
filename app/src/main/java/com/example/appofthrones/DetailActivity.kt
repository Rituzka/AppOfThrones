package com.example.appofthrones


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("key_id")

        if(savedInstanceState == null){
            val detailFragment = DetailFragment.newInstance(id)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.detailContainer,detailFragment)
                .commit()

        }
    }
}
