package com.example.appofthrones

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter: RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {

    constructor() : super() {
        itemClickListener = null
    }

    constructor (itemClickListener: ((Character, Int) -> Unit)) : super() {
        this.itemClickListener = itemClickListener
    }

    private val items = mutableListOf<Character>()
    private val itemClickListener: ((Character, Int) -> Unit)?

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var character: Character? = null
            @RequiresApi(Build.VERSION_CODES.N)
            set(value) {
                value?.let {
                    itemView.labelName.text = value.name
                    itemView.labelTitle.text = value.title

                    val overlayColor = House.getOverlayColor(value.house.name)
                    itemView.imgOverlay.background =
                        ContextCompat.getDrawable(itemView.context, overlayColor)

                    Picasso.get()
                        .load(value.img)
                        .placeholder(R.drawable.test)
                        .into(itemView.imgCharacter)
                }
                field = value
            }

        init {
            itemView.setOnClickListener {
                character?.let {
                    //hay que comunicar evento a la actividad
                    itemClickListener?.invoke(character as Character, adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
       val view =  LayoutInflater.from(parent.context)
           .inflate(R.layout.item_character, parent, false)

        return CharacterViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size

    }

    fun setCharacters(characters: MutableList<Character>){
        items.clear()
        items.addAll(characters)

        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items [position]
        holder.character = item
    }
}