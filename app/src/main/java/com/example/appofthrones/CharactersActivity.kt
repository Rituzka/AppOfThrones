package com.example.appofthrones


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_characters.*


class CharactersActivity: AppCompatActivity(),CharactersFragment.onItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val fragment = CharactersFragment()

        if(savedInstanceState == null)
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.listContainer, fragment)
            .commit()
        }

    override fun onItemClicked(character: Character) {
        if (isDetailViewAvailable()){
            showFragmentsDetail(character.id)

        }else
            launchDetailActivity(character.id)
    }

    private fun showFragmentsDetail(characterId: String) {
        val detailFragment = DetailFragment()
        //pasamos argumento al fragmento con llave, valor a través de arguments
        val args = Bundle()
        args.putString("key_id", characterId)
        detailFragment.arguments = args

       this.supportFragmentManager
           .beginTransaction()
           .replace(R.id.detailContainer, detailFragment)
           .commit()
    }
    //función lineal, a la derecha se la compara a una expresión
    private fun isDetailViewAvailable() = detailContainer != null

    private fun launchDetailActivity(characterId: String){
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)

        startActivity(intent)
     }
    }

