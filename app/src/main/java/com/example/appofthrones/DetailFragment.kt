package com.example.appofthrones

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.data_character.*
import kotlinx.android.synthetic.main.header_character.*



class DetailFragment: Fragment() {

    companion object {

        fun newInstance(id: String): DetailFragment {
            val instance = DetailFragment()
            //pasamos argumento al fragmento con llave, valor a través de arguments
            val args = Bundle()
            args.putString("key_id", id)

            instance.arguments = args

            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail,container,false)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getString("key_id")
        val character = CharactersRepo.findCharacterById(id)

        character?.let {
            with(character) {
                labelName.text = name
                labelTitle.text = title
                labelBorn.text = born
                labelParents.text = "${father} & ${mother}"
                labelQuote.text = quote
                labelSpouse.text = spouse

                val overlayColor = House.getOverlayColor(character.house.name)
                imgOverlay.background =
                    context?.let { it1 -> ContextCompat.getDrawable(it1,overlayColor) }

                val baseColor = House.getBaseColor(character.house.name)
                btnHouse.backgroundTintList =
                    context?.let { it1 -> ContextCompat.getColorStateList(it1, baseColor) }

                val idDrawable = House.getIcon(character.house.name)
                val drawable = context?.let { it1 -> ContextCompat.getDrawable(it1, idDrawable) }
                btnHouse.setImageDrawable(drawable)


            }
        }
            btnHouse.setOnClickListener {
                Toast.makeText(context, character?.house?.words, Toast.LENGTH_LONG).show()
            }
        }
    }

