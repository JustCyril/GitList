package com.example.gitlist

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/*class PostItem (@SerializedName("description") var description: String,
                @SerializedName("time") var time: String,
                @SerializedName("title") var title: String) {

}*/

class PostItem (var description: String, var time: String, var title: String) {

}