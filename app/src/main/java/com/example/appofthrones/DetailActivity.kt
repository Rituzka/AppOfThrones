package com.example.appofthrones


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*




class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("key_id")
        val character = CharactersRepo.findCharacterById(id)

        character?.let {
            with(character) {
                labelName.text = name
                labelTitle.text = title
                labelBorn.text = born
                labelParents.text = "${father} & ${mother}"
                labelQuote.text = quote
                labelSpouse.text = spouse
                button.text = house.name
            }
        }
    button.setOnClickListener {
        Toast.makeText(this, character?.house?.name, Toast.LENGTH_LONG ).show()

        }
    }
}