package br.com.marvelapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marvelapp.adapter.CharacterAdapter
import br.com.marvelapp.api.CharacterApi
import br.com.marvelapp.api.CharacterService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val api = CharacterApi()
    private lateinit var tentar : Button
    private lateinit var rvCharacters : RecyclerView
    private lateinit var loading : ProgressBar
    private lateinit var error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rvCharacters = findViewById<RecyclerView>(R.id.rvCharacters)
        loading = findViewById<ProgressBar>(R.id.loading)
        error = findViewById<TextView>(R.id.error)
        tentar = findViewById<Button>(R.id.btntentar)

        tentar.setOnClickListener {
            loading.visibility = View.VISIBLE
            tentar.visibility = View.GONE
            error.visibility = View.GONE
            chamarApi()
        }

        chamarApi()
    }

    fun chamarApi(){

        lifecycleScope.launch {
            try {
                val service = api.getRetrofit().create(CharacterService::class.java)
                val call = service.getCharacters()

                if(call.isSuccessful) {
                    val charactersList = call.body()?.data?.results ?: listOf()
                    rvCharacters.adapter = CharacterAdapter(charactersList)

                    loading.visibility = View.GONE
                    rvCharacters.visibility = View.VISIBLE
                    error.visibility = View.GONE
                    tentar.visibility = View.GONE
                }
                rvCharacters.layoutManager = LinearLayoutManager(this@MainActivity)
            } catch (ex : Exception){
                loading.visibility = View.GONE
                error.visibility = View.VISIBLE
                tentar.visibility = View.VISIBLE
            }
        }
    }

}