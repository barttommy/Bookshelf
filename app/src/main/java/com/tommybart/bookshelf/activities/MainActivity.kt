package com.tommybart.bookshelf.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tommybart.bookshelf.R
import com.tommybart.bookshelf.adapters.BookAdapter
import com.tommybart.bookshelf.asyncs.AsyncBookSearch
import com.tommybart.bookshelf.models.Book
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val bookList = ArrayList<Book>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)

        bookRecycler.layoutManager = linearLayoutManager
        bookAdapter = BookAdapter(bookList)
        bookRecycler.adapter = bookAdapter

        generateBogusData();
        AsyncBookSearch(this).execute("gentleman in moscow")
    }

    private fun generateBogusData() {
        for (i in 0..15) {
            bookList.add(Book((100..999).random() * i, "title ".plus(i), "author ".plus(i)))
        }
    }

    fun acceptResults(results: ArrayList<Book>) {

    }
}
