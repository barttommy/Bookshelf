package com.tommybart.bookshelf.asyncs

import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import com.tommybart.bookshelf.R
import com.tommybart.bookshelf.activities.MainActivity
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL

class AsyncBookSearch (private val mainActivity: MainActivity)
    : AsyncTask<String, Void, String>() {

    companion object {
        private const val TAG: String = "AsyncArrivalsLoader"
        private const val API_BASE = "https://www.googleapis.com/books/v1/volumes?"
    }

    private var failed = true;

    override fun onPostExecute(result: String?) {
        if (result == null) {
            Log.d(TAG, "onPostExecute: FAILED");
        } else if (failed) {
            Log.d(TAG, "onPostExecute: FAILED: CONNECTION ERROR");
        } else {
            Log.d("", result);
            // TODO parse and callback
        }
    }

    private fun parseJSON(s: String) {
        TODO("Implement")
    }

    // TODO: limit the amount of responses to < 10?
    override fun doInBackground(vararg params: String?): String? {
        val connection : HttpURLConnection
        try {
            val buildUrl = Uri.parse(API_BASE).buildUpon()
            buildUrl.appendQueryParameter("q", params[0])
            buildUrl.appendQueryParameter("key", mainActivity.resources.getString(R.string.api_key))

            val url = URL(buildUrl.build().toString())
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            val responseMsg = connection.responseMessage
            Log.d(
                TAG, String.format(
                    "doInBackground: responseCode: %s responseText: %s",
                    responseCode, responseMsg
                )
            )

            val inputStream: InputStream
            if (responseCode == HTTP_OK) {
                inputStream = connection.inputStream
                failed = false
            } else {
                inputStream = connection.errorStream
                failed = true
            }

            val result = inputStream.bufferedReader().use(BufferedReader::readText)
            connection.disconnect()
            return result

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}