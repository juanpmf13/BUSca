package me.dio.busca.dados.ui.map

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.dio.busca.MainActivity
import me.dio.busca.R
import me.dio.busca.dados.Parada
import me.dio.busca.dados.V
import me.dio.busca.dados.Veiculo
import me.dio.busca.dados.VeiculoLinha
import me.dio.busca.dados.servicos.AppDatabase
import me.dio.busca.databinding.ActivityMapsBinding
import okhttp3.internal.wait
import java.util.concurrent.TimeUnit

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var posi : ArrayList<Parada>
    lateinit var veiculo : List<Veiculo>
    lateinit var veiculoLinha : List<V>
    lateinit var maps : GoogleMap
    var iniciaBusca = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)






        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapsActivity)

        val codigo = intent.getIntExtra("codigo",0)

            when (codigo) {
                1 -> {
                    posi = intent.getParcelableArrayListExtra("paradaportermo")!!
                }
                2 -> {
                    veiculoLinha = intent.getParcelableArrayListExtra("veiculolinha")!!
                }
                4 -> iniciaBusca = true
            }

        CoroutineScope(Dispatchers.IO).launch {
        if(iniciaBusca) {

                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "veiculo"
                ).build()
                val veiculoDao = db.veiculoDao()
                val veiculosmono = veiculoDao.carregaVeiculo()
                veiculo = veiculosmono
            }
            iniciaBusca=false
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

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

        TimeUnit.SECONDS.sleep(2)
        mMap.setMinZoomPreference(15.0f)
        // Add a marker in Sydney and move the camera
        // Add a marker in Sydney and move the camera
        // Add a marker in Sydney and move the camera
        // Add a marker in Sydney and move the camera

        if(::posi.isInitialized) {
            if(posi.size !=0) {
                posi.filter {
                    it.cp != null
                }.forEach {
                    mMap.addMarker((MarkerOptions().position(LatLng(it.py, it.px)).title(it.np)))
                }
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            posi.get(0).py,
                            posi.get(0).px
                        )
                    )
                )

            }else{
                Toast.makeText(this@MapsActivity,"nenhuma parada encontrada",Toast.LENGTH_SHORT).show()
            }
        }

        if(::veiculoLinha.isInitialized){
            if(veiculoLinha.size !=0){
                val filtado = veiculoLinha.filter {
                    true
                }
                filtado.forEach {

                        mMap.addMarker(
                            (MarkerOptions().position(LatLng(it.py, it.px))
                                .title("posição do veiculo"))
                        )

                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(filtado.get(0).py,filtado.get(0).px)))

            }else{
                Toast.makeText(this@MapsActivity,"nenhuma parada encontrada",Toast.LENGTH_SHORT).show()
            }
        }


            if(::veiculo.isInitialized){
                if(veiculo.size !=0){
                    val filtado = veiculo!!.filter {
                        it.indo != null
                    }
               filtado.forEach {

                    mMap.addMarker((MarkerOptions().position(LatLng(it.py,it.px)).title(it.vindo+"para"+ it.indo)))
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(filtado.get(0).py,filtado.get(0).px)))

            }else{
                    Toast.makeText(this@MapsActivity,"nenhuma parada encontrada",Toast.LENGTH_SHORT).show()
                }
        }


    }
}