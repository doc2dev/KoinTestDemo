package com.doc2dev.seedr.ui.seedList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doc2dev.seedr.R
import com.doc2dev.seedr.database.entities.SeedEntry

/**
 * Created by Eston on 06/12/2018.
 */
class SeedListAdapter(private val seedEntries: List<SeedEntry>) :
    RecyclerView.Adapter<SeedItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeedItemViewHolder =
        SeedItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_seed_entry, parent, false)
        )

    override fun getItemCount(): Int = seedEntries.size

    override fun onBindViewHolder(holder: SeedItemViewHolder, position: Int) {
        holder.bind(seedEntries[position])
    }
}