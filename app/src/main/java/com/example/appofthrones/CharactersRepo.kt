package com.example.appofthrones

object CharactersRepo {
    val characters: MutableList<Character> = mutableListOf()
    get() {
        if (field.isEmpty())
        field.addAll(dummyCharacters())

        return field
    }

   private fun dummyCharacters(): MutableList<Character> {
      return (1..10).map {
           intToCharacter(it)
       }.toMutableList()

   }
    private fun intToCharacter(int: Int): Character {

       return Character(
            name = "Personaje ${int}",
            title = "Titulo ${int}",
            born = "Nació en ${int}",
            actor = "Actor ${int}",
            quote = "Frase ${int}",
            father = "Father ${int}",
            mother = "Mother ${int}",
            spouse = "Spouse ${int}",
            house = House(
                name = "House ${int}",
                region = "Region ${int}",
                words = "Lema ${int}"
            )
        )
    }
}

