package com.example.appofthrones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail.button
import kotlinx.android.synthetic.main.fragment_detail.labelBorn
import kotlinx.android.synthetic.main.fragment_detail.labelName
import kotlinx.android.synthetic.main.fragment_detail.labelParents
import kotlinx.android.synthetic.main.fragment_detail.labelQuote
import kotlinx.android.synthetic.main.fragment_detail.labelSpouse
import kotlinx.android.synthetic.main.fragment_detail.labelTitle

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
                button.text = house.name
            }
        }
            button.setOnClickListener {
                Toast.makeText(context, character?.house?.words, Toast.LENGTH_LONG).show()
            }
        }
    }

