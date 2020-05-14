package com.tommybart.bookshelf.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tommybart.bookshelf.R
import com.tommybart.bookshelf.helpers.inflate
import com.tommybart.bookshelf.models.Book
import kotlinx.android.synthetic.main.book_row_item.view.*

class BookAdapter (private val bookList: ArrayList<Book>)
    : RecyclerView.Adapter<BookAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflatedView = parent.inflate(R.layout.book_row_item, false)
        return BookHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bindBook(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.count()
    }

    class BookHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var book: Book? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("onClick", "Clicked")
            //TODO could open a detail activity or something
        }

        fun bindBook(book: Book) {
            this.book = book
            view.author_text_view.text = book.author
            view.title_text_view.text = book.title
            view.isbn_text_view.text = book.isbn.toString()
        }
    }
}