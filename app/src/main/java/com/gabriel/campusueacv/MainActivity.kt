package com.gabriel.campusueacv

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_campus.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var name : String
    private var latcher = false

    private var campi: List<Campus> = listOf(
        Campus(
            "Reitoria",
            "Manaus",
            "Av. Djalma Batista, 3578. Flores, Manaus - AM",
            "(92) 3214-5778",
            R.drawable.ic_location_city_24px,
            -3.083805,
            -60.023919

        ),
        Campus(
            "ENS - Escola Normal\nSuperior",
            "Manaus",
            "Avenida Djalma Batista, nº 2.470, Chapada, Manaus - AM",
            "(92) 3878-7721",
            R.drawable.ic_history_edu_24px,
            -3.092453,
            -60.023101
        ),
        Campus(
            "ESAT - Escola Superior\nde Artes e Turismo",
            "Manaus",
            "Rua Leonardo Malcher, nº 1.728, Praça 14 de Janeiro, Manaus - AM",
            "(92) 3878-4415",
            R.drawable.ic_brush_24px,
            -3.125968,
            -60.016356
        ),
        Campus(
            "ESCS - Escola Superior\nde Ciências e Saúde",
            "Manaus",
            "Avenida Carvalho Leal, nº 1.777, Cachoeirinha, Manaus - AM",
            "(92) 3878-4380",
            R.drawable.ic_science_24px,
            -3.119196,
            -60.005978
        ),
        Campus(
            "ESCS - Escola Superior\nde Ciências Sociais",
            "Manaus",
            "Avenida Castelo Branco, nº 504, Cachoeirinha, Manaus - AM",
            "(92) 3878-7801",
            R.drawable.ic_gavel_24px,
            -3.125615,
            -60.022110
        ),
        Campus(
            "EST - Escola Superior\nde Tecnologia",
            "Manaus",
            "Avenida Darcy Vargas, nº 1.200, Parque 10 de Novembro, Manaus - AM",
            "(92) 3878-4308",
            R.drawable.engineering_24px,
            -3.091644,
            -60.017688
        ),
        Campus(
            "CESIT - Centro de Estudos\nSuperiores de Itacoatiara",
            "Itacoatiara",
            "Rua Mário Andreazza, s/nº, São Francisco, Itacoatiara - AM",
            "(92) 3521-4293",
            R.drawable.ic_school_24px,
            -3.125339,
            -58.431307
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvCampus.adapter = CampusAdapter(campi) {
            val intent = Intent(this, infoUniversidades::class.java).apply {
                putExtra(infoUniversidades.CAMPUS_EXTRA, it)
            }
            startActivity(intent)
        }
        done_button.setOnClickListener { buttonClickHandlle(it) }
        text_nome.setOnClickListener { changeName() }
        retriveData()
        if(latcher){
            setVisibility()
        }

    }

    class CampusAdapter(
        private val campus: List<Campus>,
        private val onItemClick: (Campus) -> Unit

    ): RecyclerView.Adapter<CampusAdapter.CampusViewHolder>() {
        class CampusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampusViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_campus, parent, false)
            return CampusViewHolder(itemView)
        }

        override fun getItemCount() = campus.size

        override fun onBindViewHolder(holder: CampusViewHolder, position: Int) {
            holder.itemView.tvCampusName.text = campus[position].campusName
            holder.itemView.tvCityName.text = campus[position].cityName

            holder.itemView.ivImageCampus.setImageResource(campus[position].imageLocal)
            holder.itemView.setOnClickListener {
                onItemClick(campus[position])
            }
        }
    }

    private fun changeName(){
        text_nome.visibility = View.GONE
        editView.visibility = View.VISIBLE
        done_button.visibility = View.VISIBLE

    }

    private fun buttonClickHandlle(view: View){


        val stringPlusNome = editView.text.toString()
        name = "Bem vindo ${stringPlusNome}"


        val contentToUnderline = SpannableString(name)
        contentToUnderline.setSpan(UnderlineSpan(),0,name.length,0)

        text_nome.text = contentToUnderline
        latcher = true

        editView.visibility = View.GONE
        done_button.visibility = View.GONE
        text_nome.visibility = View.VISIBLE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        val preferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString("name",text_nome.text.toString())
        editor.putBoolean("latche",latcher)
        editor.apply()

    }

    fun retriveData(){
        val preferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE)

        name = preferences.getString("name","").toString()
        latcher = preferences.getBoolean("latche",false)

    }

    fun setVisibility(){

        val contentToUnderline = SpannableString(name)
        contentToUnderline.setSpan(UnderlineSpan(),0,name.length,0)

        text_nome.text = contentToUnderline
        editView.visibility = View.GONE
        done_button.visibility = View.GONE
        text_nome.visibility = View.VISIBLE
    }

}


//gabriel