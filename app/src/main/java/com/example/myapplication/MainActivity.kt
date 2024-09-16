package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }

    }



    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "listView") {
            composable("listView") {
                ListView(fetchList(), fetchImages(), navController)
            }
            composable("detailItem/{name}/{id}") { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    ?: R.drawable.ic_launcher_background
                    detailItem(name = name, id = id)
                }


            }}}



    fun fetchList() :List<String>{
        return arrayListOf("Title : Game0","Title : Game1","Title : Game2 ","Title : Game3","Title : Game4",
            "Title : Game5","Title : Game6","Title : Game7 ","Title : Game8","Title : Game9")
    }

    fun fetchImages() :List<Int>{
        return arrayListOf(R.drawable.picture1,R.drawable.picture2jpeg,R.drawable.picture3,R.drawable.picture4, R.drawable.picture5,
            R.drawable.picture6,R.drawable.picture7,R.drawable.picture8,R.drawable.picture10)
    }
