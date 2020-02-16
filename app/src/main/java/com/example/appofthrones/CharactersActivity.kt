package com.example.appofthrones


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_characters.*


class CharactersActivity: AppCompatActivity(),CharactersFragment.onItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        if (savedInstanceState == null) {
            val fragment = CharactersFragment()
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.listContainer, fragment)
                .commit()
        }
    }
    override fun onItemClicked(character: Character) {
        if (isDetailViewAvailable())
            showFragmentDetails(character.id)
        else
            launchDetailActivity(character.id)
    }
    //función lineal, a la derecha se la compara a una expresión
    private fun isDetailViewAvailable() = detailContainer != null

    private fun showFragmentDetails(characterId: String) {
    val detailFragment = DetailFragment.newInstance(characterId)

        supportFragmentManager
           .beginTransaction()
           .replace(R.id.detailContainer, detailFragment)
           .commit()
    }

    private fun launchDetailActivity(characterId: String){
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)

        startActivity(intent)
    }
}

