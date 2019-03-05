package com.doc2dev.seedr.ui.seedList

import android.content.Intent
import android.os.Bundle
import com.doc2dev.seedr.R
import com.doc2dev.seedr.database.entities.SeedEntry
import com.doc2dev.seedr.ui.MainActivity
import com.doc2dev.seedr.viewmodel.SeedViewModel
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.support.v4.SupportFragmentController
import java.util.*


@RunWith(RobolectricTestRunner::class)
class SeedListFragmentTest {
    lateinit var fragment: SeedListFragment

    @Before
    fun initFragment() {
        val vModel: SeedViewModel = Mockito.mock(SeedViewModel::class.java)
        `when`(vModel.getAllSeeds()).thenReturn(getMockSeedFlowable())
        val mockModule = module {
            viewModel(override=true) { vModel }
        }
        loadKoinModules(listOf(mockModule))
        val controller = SupportFragmentController.of(
            SeedListFragment(),
            MainActivity::class.java,
            Intent()
        )
        fragment = controller.create(R.id.fragment_container, Bundle())
            .start()
            .resume()
            .visible()
            .get()
    }

    private fun getMockSeedFlowable(): Flowable<List<SeedEntry>>? {
        val seedList = listOf(
            SeedEntry(
                "Foo",
                "maize",
                2,
                9384756L,
                "0723220576",
                false,
                Date()
            ),
            SeedEntry(
                "Bar",
                "rice",
                2,
                9384756L,
                "0723220576",
                false,
                Date()
            )
        )
        return Flowable.just(seedList)
    }

    @Test
    fun testSeedListDisplayed() {
        assertThat(fragment.adapter.itemCount, `is`(2))
    }

}