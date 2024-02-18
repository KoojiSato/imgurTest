package com.example.imgurtest.task

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.example.imgurtest.ImageAdapter
import java.net.URL
import kotlin.coroutines.coroutineContext


class RetrieveImageTask {

    suspend fun getBitMapList() : Array<Bitmap>{
        var urls : ArrayList<String> = RetrieveImageLinksTask().authToken()
        var arrayBitmap : Array<Bitmap> = arrayOf()
        for(url in urls){
            var bitMap = getBitmapImage(url)
            if(bitMap != null) {
                arrayBitmap += bitMap
            }
        }
        return arrayBitmap
    }


    fun getBitmapImage(url:String) : Bitmap?{
        var url = URL(url)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }

}