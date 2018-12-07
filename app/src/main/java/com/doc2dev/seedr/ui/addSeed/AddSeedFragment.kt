package com.doc2dev.seedr.ui.addSeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.doc2dev.seedr.R
import com.doc2dev.seedr.ui.MainActivity
import com.doc2dev.seedr.util.attachToLifecycle
import com.doc2dev.seedr.util.makeShortSnackbar
import com.doc2dev.seedr.util.text
import com.doc2dev.seedr.util.validateNotEmpty
import com.doc2dev.seedr.viewmodel.SeedViewModel
import kotlinx.android.synthetic.main.fragment_add_seed.*
import timber.log.Timber


/**
 * Created by Eston on 06/12/2018.
 */
class AddSeedFragment : Fragment() {
    private lateinit var parentActivity: MainActivity
    private lateinit var rootView: View
    private lateinit var viewModel: SeedViewModel
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
        rootView = view
        parentActivity = activity as MainActivity
        parentActivity.toggleBackButton(true)
        viewModel = ViewModelProviders.of(parentActivity).get(SeedViewModel::class.java)
        parentActivity.title = "Add Seed"
        populateSeedTypes()
        populateSeedQuantities()
        submitButton.setOnClickListener { validateAndSave() }
    }

    private fun validateAndSave() {
        if (allFieldsValid()) {
            toggleLoading(true)
            val completable = viewModel.persistSeedEntry(
                brandName.text(),
                seedTypes[seedTypeSelector.selectedItemPosition],
                seedQuantities[seedQuantitySelector.selectedItemPosition],
                uniqueId.text().toLong(),
                phoneNumber.text()
            )
            val disposable = completable.subscribe({
                toggleLoading(false)
                rootView.makeShortSnackbar("Seed added successfully!")
                findNavController().navigateUp()
            }, {
                toggleLoading(false)
                Timber.d("Error: ${it.message}")
                rootView.makeShortSnackbar("Sorry, the operation failed.")
            })
            attachToLifecycle(disposable)
        }
    }

    private fun toggleLoading(isLoading: Boolean) {
        val progressVisibility = if (isLoading) VISIBLE else GONE
        val buttonVisibility = if (isLoading) GONE else VISIBLE
        progressView.visibility = progressVisibility
        submitButton.visibility = buttonVisibility
    }

    private fun allFieldsValid(): Boolean {
        val isSeedTypeSelected = seedTypeSelector.selectedItemPosition >= 0
        val isSeedQuantitySelected = seedQuantitySelector.selectedItemPosition >= 0
        if (!isSeedQuantitySelected) {
            rootView.makeShortSnackbar("Seed quantity is required")
            return false
        }
        if (!isSeedTypeSelected) {
            rootView.makeShortSnackbar("Seed type is required")
            return false
        }
        return brandName.validateNotEmpty() &&
            uniqueId.validateNotEmpty() &&
            phoneNumber.validateNotEmpty()
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