package br.com.marvelapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.marvelapp.models.CharacterMarvel
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Habilita o botão de voltar
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val character = intent.getSerializableExtra("item_character") as CharacterMarvel

        supportActionBar?.title = character.name

        val  image = findViewById<ImageView>(R.id.image)
        Picasso.get().load(character.getImage()).into(image)

        val nome = findViewById<TextView>(R.id.name)
        nome.text = character.name

        val description = findViewById<TextView>(R.id.description)
        description.text = character.description

        val serie = findViewById<TextView>(R.id.series)
        if (character.series.items.isNotEmpty()){

            val builder = StringBuilder()
            character.series.items.forEach {
                builder.append(it.name).append("\n")
            }
            serie.text = builder.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Chama o método de voltar padrão
        return true
    }

}