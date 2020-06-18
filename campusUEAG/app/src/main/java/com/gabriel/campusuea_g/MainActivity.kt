package com.gabriel.campusuea_g

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_campus.view.*

class MainActivity : AppCompatActivity() {
    private var campi: List<Campus> = listOf(
        Campus(
            "Reitoria",
            "Manaus",
            "Av. Djalma Batista, 3578. Flores, Manaus - AM",
            "(92) 3214-5778"
        ),
        Campus(
            "Escola Normal Superior",
            "Manaus",
            "Avenida Djalma Batista, nº 2.470, Chapada, Manaus - AM",
            "(92) 3878-7721"
        ),
        Campus(
            "Escola Superior de Artes e Turismo",
            "Manaus",
            "Rua Leonardo Malcher, nº 1.728, Praça 14 de Janeiro, Manaus - AM",
            "(92) 3878-4415"
        ),
        Campus(
            "Escola Superior de Ciências e Saúde",
            "Manaus",
            "Avenida Carvalho Leal, nº 1.777, Cachoeirinha, Manaus - AM",
            "(92) 3878-4380"
        ),
        Campus(
            "Escola Superior de Ciências Sociais",
            "Manaus",
            "Avenida Castelo Branco, nº 504, Cachoeirinha, Manaus - AM",
            "(92) 3878-7801"
        ),
        Campus(
            "Escola Superior de Tecnologia",
            "Manaus",
            "Avenida Darcy Vargas, nº 1.200, Parque 10 de Novembro, Manaus - AM",
            "(92) 3878-4308"
        ),
        Campus(
            "Centro de Estudos Superiores de Itacoatiara",
            "Manaus",
            "Rua Mário Andreazza, s/nº, São Francisco, Itacoatiara - AM",
            "(92) 3521-4293"
        ),
        Campus(
            "Centro de Estudos Superiores de Itacoatiara",
            "Itacoatiara",
            "Rua Mário Andreazza, s/nº, São Francisco, Itacoatiara - AM",
            "(92) 3521-4293"
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvCampus.adapter = CampusAdapter(campi) {
            startActivity(Intent(this, CampusDetailActivity::class.java))
        }

//
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

            holder.itemView.setOnClickListener {
                onItemClick(campus[position])
            }
        }
    }
}
