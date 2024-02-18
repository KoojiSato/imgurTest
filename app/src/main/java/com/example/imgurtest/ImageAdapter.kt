package com.example.imgurtest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.imgurtest.task.RetrieveImageTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ImageAdapter(imageList: Array<Bitmap>, context: Context) : BaseAdapter() {

    private var images: Array<Bitmap> = imageList

    private var context: Context = context;

    private var li : LayoutInflater = LayoutInflater.from(context)


    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(p0: Int): Any {
        return images.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        var v : View
        if(view == null){
            v = li.inflate(R.layout.row_itens, viewGroup, false)
        }else{
            v = view
        }
        var iv: ImageView = v.findViewById(R.id.image_view)
        iv.setImageBitmap(images[i])
        return iv
    }

}