package com.doc2dev.seedr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.doc2dev.seedr.R
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_add_seed.*


/**
 * Created by Eston on 06/12/2018.
 */
class AddSeedFragment: Fragment() {
    private lateinit var parentActivity: AppCompatActivity
    private val seedTypes = arrayOf(
        "Maize",
        "Sorghum",
        "Rice",
        "Beans"
    )
    private val seedQuantities = arrayOf(2, 5, 10, 25)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_seed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentActivity = activity as AppCompatActivity
        parentActivity.title = "Add Seed"
        populateSeedTypes()
        populateSeedQuantities()
    }

    private fun populateSeedQuantities() {
        val seedQuantityStrings = seedQuantities.map {
            "${it}kg"
        }
        val arrayAdapter = ArrayAdapter<String>(
            parentActivity,
            android.R.layout.simple_spinner_item,
            seedQuantityStrings
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        seedQuantitySelector.adapter = arrayAdapter
    }

    private fun populateSeedTypes() {
        val arrayAdapter = ArrayAdapter<String>(
            parentActivity,
            android.R.layout.simple_spinner_item,
            seedTypes
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        seedTypeSelector.adapter = arrayAdapter
    }
}