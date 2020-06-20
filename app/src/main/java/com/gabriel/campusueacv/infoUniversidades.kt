package com.gabriel.campusueacv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_info_universidades.*
import kotlinx.android.synthetic.main.list_item_campus.*

class infoUniversidades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_universidades)
        intent.getParcelableExtra<Campus>(CAMPUS_EXTRA)?.let { Campus ->
            tvName.text = Campus.campusName
            tvCidade.text = Campus.cityName
            tvAdressInput.text = Campus.address
            tvFoneInput.text = Campus.phoneNumber

            val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
            mapFragment.getMapAsync {
                it.uiSettings.isZoomControlsEnabled = true

                val latLng = LatLng(Campus.latitude, Campus.longitude)

                val marker = MarkerOptions().position(latLng)

                it.addMarker(marker)

                it.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                it.moveCamera(CameraUpdateFactory.zoomTo(15f))
            }
        }
    }

    companion object {
        const val CAMPUS_EXTRA = "Campus"
    }

}