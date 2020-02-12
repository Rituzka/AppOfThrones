package com.example.appofthrones

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class CharactersFragment: Fragment() {

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)

        list
    }

    val adapter: CharactersAdapter by lazy {
        val adapter= CharactersAdapter { item, position ->
            showDetails(item.id)
        }
        adapter
    }

   lateinit var clickListener : onItemClickListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if(context is onItemClickListener)
            clickListener = context
        else
            throw IllegalArgumentException("Attached activity doesn't implement CharacterFragment.onItemClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characters: MutableList<Character> = CharactersRepo.characters
        adapter.setCharacters(characters)

        list.adapter = adapter

    }
    fun showDetails(characterId: String){
        val intent= Intent(context, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)

        startActivity(intent)

    }

    interface onItemClickListener {
        fun onItemClicked(character: Character){

        }
    }
}