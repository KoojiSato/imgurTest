package com.example.imgurtest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.example.imgurtest.task.RetrieveImageTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var gridView : GridView

    var images : Array<Bitmap> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        gridView = findViewById(R.id.grid_view)
        var context : Context = this

        var routine = CoroutineScope(Dispatchers.Default).launch {
            images = RetrieveImageTask().getBitMapList()
        }
        while(!routine.isCompleted || routine.isActive){

        }
        var imgAdapter = ImageAdapter(images, context)
        gridView.adapter = imgAdapter

    }
}