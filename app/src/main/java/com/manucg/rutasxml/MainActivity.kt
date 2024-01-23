package com.manucg.rutasxml

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.manucg.rutasxml.databinding.ActivityMainBinding
import com.manucg.rutasxml.model.dao.DaoSAX
import com.manucg.rutasxml.model.dao.DaoSimpleXML
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val svm : ServiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        var daoSimpleXML = DaoSimpleXML(applicationContext, svm)
        daoSimpleXML.ProcesarArchivoXMLInterno()
        svm.miAdapter.notifyDataSetChanged()
        //pruebaDaoSAX()
    }

    private fun pruebaDaoSAX() {
        var daoSAX : DaoSAX = DaoSAX(applicationContext,svm)
        daoSAX.procesarArchivoAssetsXMLSAX()
    }

    private fun setup() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}