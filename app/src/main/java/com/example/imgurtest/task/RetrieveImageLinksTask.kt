package com.example.imgurtest.task

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.internal.LinkedTreeMap
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.ArrayList



class RetrieveImageLinksTask {


    suspend fun findLinks(client : OkHttpClient) : ArrayList<String>{
        val mediaType: MediaType? = MediaType.parse("text/plain")
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val request: Request = Request.Builder()
            .url("https://api.imgur.com/3/gallery/search/?q=cats")
            .method("GET", null)
            .addHeader("Authorization", "Client-ID 1ceddedc03a5d71")
            .build()
        val response: okhttp3.Response = client.newCall(request).execute()
        val gson = Gson()
        val responseBody = GsonBuilder().setLenient().setPrettyPrinting().create().toJson(JsonParser.parseString(response.body()?.string()))
        val map = gson.fromJson<Map<String, List<LinkedTreeMap<String, List<LinkedTreeMap<String,String>>>>>>(responseBody, MutableMap::class.java)
        val data = map.get("data")
        var listLinks = ArrayList<String>()
        if(data!=null) {
            for (a:LinkedTreeMap<String, List<LinkedTreeMap<String, String>>> in data) {
                var post : List<LinkedTreeMap<String, String>>? = a.get("images")
                if (post != null) {
                    for(b:LinkedTreeMap<String, String> in post){
                        var link = b.get("link")
                        if(link!= null && (link.contains("jpg") || link.contains("jpeg"))){
                            listLinks.add(link)
                        }
                    }
                }
            }
        }
        println(listLinks.size)
        return listLinks
    }

    suspend fun authToken() : ArrayList<String>{
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .build()
        val mediaType: MediaType? = MediaType.parse("text/plain")
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(JSON, "{}")
        val request: Request = Request.Builder()
            .url("https://api.imgur.com/oauth2/authorize?client_id=1ceddedc03a5d71&response_type=token")
            .method("GET", null)
            .build()
        val response: okhttp3.Response = client.newCall(request).execute()
        if(response.isSuccessful){
            return findLinks(client)
        }
        return ArrayList<String>()
    }

}