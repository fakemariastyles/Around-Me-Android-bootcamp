package com.workshop.aroundme.local.datasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.model.LocalPlace
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.empty
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@MediumTest
@RunWith(AndroidJUnit4::class)
class PlaceLocalDataSourceTest {

    private lateinit var placeLocalDataSource: PlaceLocalDataSource
    private lateinit var db: AppDatabase


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        placeLocalDataSource = PlaceLocalDataSource(db.placeDao())
    }


    @Test
    fun table_is_empty() {
        val result = placeLocalDataSource.getStarredPlaces()
        assertThat(result, `is`(empty()))
    }

    @Test
    fun starPlace_must_add_a_row(){
        val place = LocalPlace(1 , "name" , "address" , true)
        placeLocalDataSource.starPlace(place)
        assertEquals(listOf(place) , placeLocalDataSource.getStarredPlaces())
    }
    @After
    @Throws(IOException::class)
    fun destroy() {
        db.clearAllTables()
        db.close()
    }

}