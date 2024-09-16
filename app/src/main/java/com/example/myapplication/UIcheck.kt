package com.example.myapplication

import android.annotation.SuppressLint
import android.util.Log
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListView(list: List<String>,listpic: List<Int>, navController: NavHostController){



    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)) {




        TopAppBar(
            title = { Text(text = "Landscapes", color = Color.White) },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),

        )



            LazyColumn(modifier = Modifier.fillMaxSize()){

                itemsIndexed(list){
                        index,name ->
                    val pic = listpic.getOrNull(index) ?: R.drawable.ic_launcher_background


                    if(pic !=null){
                        ItemView(name = name, id =pic , onCliked ={navController.navigate("detailItem/$name/$pic")} )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))


                }


            }

        }



}

@Composable
fun ItemView(name :String,id :Int,onCliked: ()->Unit ){


    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
        Image(
            contentDescription = "image",
            modifier = Modifier
                .padding(5.dp)
                .size(100.dp, 200.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = id)
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                .background(Color.White)
                .fillMaxWidth()
                .size(120.dp)
                .clickable { onCliked() }
        ) {
            Text(

                text = name,
                color = Color.DarkGray,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp)
            )


            var ratingBar by remember { mutableFloatStateOf(0f) }
            RatingBar(
                modifier = Modifier.padding(top = 50.dp),
                rating = ratingBar,
                onClick = { index ->
                    ratingBar = index

                }
            )
        }


    }
}


@Composable
fun detailItem(name: String,id: Int){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)) {

        Box(modifier = Modifier.fillMaxWidth().padding(top = 40.dp, start = 10.dp, end = 10.dp).clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)).background(Color.White)) {

            Image(painter = painterResource(id = id), modifier = Modifier
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                , contentDescription = "image")

        }

        Text(text = name, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start, modifier = Modifier.padding(start = 10.dp, top = 20.dp))

    }
}



@Composable
fun RatingBar(
    rating: Float,
    onClick: (Float) -> Unit,
    modifier: Modifier = Modifier,
    star: Int = 5,
) {
    Row(modifier = modifier) {
        (1..star).forEach { index ->
            val selectStart = index <= rating
            Image(
                painter = if (selectStart) painterResource(R.drawable.baseline_star_rate_24)
                else painterResource(R.drawable.starbar),
                contentDescription = "rate star",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable {
                        onClick(index.toFloat())
                    }
                    .padding(4.dp)
                    .size(30.dp)
            )
        }
    }
}

