package com.doc2dev.seedr.ui.seedList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.doc2dev.seedr.database.entities.SeedEntry
import com.doc2dev.seedr.util.localString
import kotlinx.android.synthetic.main.item_seed_entry.view.*

/**
 * Created by Eston on 06/12/2018.
 */
class SeedItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(seedEntry: SeedEntry) {
        with(itemView) {
            seedBrandName.text = seedEntry.brandName
            seedSaleDate.text = seedEntry.dateCreated.localString()
            seedType.text = seedEntry.seedType
            seedQuantity.text = "${seedEntry.quantity} Kg"
            seedUniqueId.text = seedEntry.uniqueId.toString()
        }
    }
}