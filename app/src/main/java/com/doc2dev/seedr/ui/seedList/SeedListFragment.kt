package com.doc2dev.seedr.ui.seedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doc2dev.seedr.R
import com.doc2dev.seedr.database.entities.SeedEntry
import com.doc2dev.seedr.ui.MainActivity
import com.doc2dev.seedr.util.attachToLifecycle
import com.doc2dev.seedr.viewmodel.SeedViewModel
import kotlinx.android.synthetic.main.fragment_seeds_list.*
import timber.log.Timber

/**
 * Created by Eston on 06/12/2018.
 */
class SeedListFragment: Fragment() {
    lateinit var parentActivity: MainActivity
    lateinit var viewModel: SeedViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_seeds_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentActivity = activity as MainActivity
        parentActivity.toggleBackButton(false)
        viewModel = ViewModelProviders.of(parentActivity).get(SeedViewModel::class.java)
        parentActivity.title = "Seed List"
        addFab.setOnClickListener {
            findNavController().navigate(R.id.openAddSeed)
        }
        observeSeedList()
    }

    private fun observeSeedList() {
        val flowable = viewModel.getAllSeeds()
        val disposable = flowable.subscribe({
            showSeeds(it)
        }, {
            Timber.d("Seed retrieval error: ${it.message}")
        })
        attachToLifecycle(disposable)
    }

    private fun showSeeds(seedEntries: List<SeedEntry>) {
        val sortedEntries = seedEntries.sortedByDescending {
            it.dateCreated.time
        }
        val adapter = SeedListAdapter(sortedEntries)
        val layoutManager = LinearLayoutManager(parentActivity, RecyclerView.VERTICAL, false)
        seedEntryRecycler.layoutManager = layoutManager
        seedEntryRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}