package me.dio.busca.dados.ui.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import me.dio.busca.R
import me.dio.busca.dados.Parada
import me.dio.busca.dados.Post
import me.dio.busca.dados.V
import me.dio.busca.dados.Veiculo
import me.dio.busca.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var posi : ArrayList<Parada>
    lateinit var veiculo : ArrayList<Veiculo>
    lateinit var veiculos : ArrayList<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        posi= intent.getParcelableArrayListExtra("parada")!!
   //     veiculo = intent.getParcelableExtra("veiculo")!!

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        if(posi!= null)
        if(::posi.isInitialized) {
            posi.filter {
                it.cp!= null
            }.forEach {
                mMap.addMarker((MarkerOptions().position(LatLng(it.py,it.px)).title(it.np)))
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(posi.get(0).py,posi.get(0).px)))
            mMap.setMinZoomPreference(15.0f)
        }

       /*
        if(veiculo!= null){
            if(::veiculo.isInitialized){
                veiculo.filter {
                    it.hr!= null
                }.forEach {
                    it.l.forEach {
                        it.getVs().forEach {
                            mMap.addMarker((MarkerOptions().position(LatLng(it.py,it.px)).title(it.p.toString())))
                        }
                    }
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(posi.get(0).py,posi.get(0).px)))
            }
        }

        if(veiculos!= null)
            if(::veiculos.isInitialized) {
                veiculos.filter {
                    it.a!= null
                }.forEach {
                    mMap.addMarker((MarkerOptions().position(LatLng(it.py,it.px)).title(it.p.toString())))
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(posi.get(0).py,posi.get(0).px)))
            }*/

    }
}