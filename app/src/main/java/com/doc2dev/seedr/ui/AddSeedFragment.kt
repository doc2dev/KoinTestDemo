package com.doc2dev.seedr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.doc2dev.seedr.R

/**
 * Created by Eston on 06/12/2018.
 */
class AddSeedFragment: Fragment() {
    private lateinit var parentActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_seed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentActivity = activity as AppCompatActivity
        parentActivity.title = "Add Seed"
    }
}