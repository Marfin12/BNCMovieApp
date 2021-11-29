package com.example.bncmovieapp.unit_test

import com.example.bncmovieapp.core.data.source.local.room.Converters
import org.junit.Assert
import org.junit.Test

class ConverterTest {
    val mockedData = "['budi', 'rani']"

    @Test
    fun convertStringResponse_EqualTo_ListOfArrayString() {
        val listString = Converters.fromString(mockedData)
        val expectedListString = listOf("budi", "rani")

        Assert.assertEquals(listString, expectedListString)
    }

    @Test
    fun mapResponsesToEntities_EqualTo_ListOfMovieEntity() {
        val mockedListString = arrayListOf<String?>()
        mockedListString.add("budi")
        val listString = Converters.fromArrayList(mockedListString)

        Assert.assertEquals(listString, "[\"budi\"]")
    }
}