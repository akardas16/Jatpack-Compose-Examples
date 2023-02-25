package com.akardas.jatpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akardas.jatpackcompose.ui.theme.JatpackComposeTheme
import com.akardas.jatpackcompose.ui.theme.Typography
import com.akardas.jatpackcompose.ui.theme.Typog

class MainActivity : ComponentActivity() {
    val allPersons = listOf<PersonModel>(
        PersonModel(Color.Red,"Abdullah",16),
        PersonModel(Color.Blue,"Mehmet",35),
        PersonModel(Color.Cyan,"Ali",45),
        PersonModel(Color.Green,"Sude",85),
        PersonModel(Color.Yellow,"Süleyman",17),
        PersonModel(Color.Magenta,"Serdar",13),
        PersonModel(Color.Cyan,"Serpil",28),
        PersonModel(Color.Blue,"Seda",24),
        PersonModel(Color.Black,"Rukiye",23),
        PersonModel(Color.Cyan,"Ali",45),
        PersonModel(Color.Green,"Sude",85),
        PersonModel(Color.Yellow,"Süleyman",17),
        PersonModel(Color.Magenta,"Serdar",13),
        PersonModel(Color.Cyan,"Serpil",28),
        PersonModel(Color.Blue,"Seda",24),
        PersonModel(Color.Black,"Rukiye",23),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    ShowLazyRow(allPersons)
                }
            }
        }
    }
}



@Composable
fun ShowLazyColum(allPersonModel: List<PersonModel>){
    LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(8.dp)){
        items(allPersonModel){item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clip(CircleShape)
                        .background(color = item.color).padding(start = 16.dp)
                )
                Text(text = item.name, color = item.color, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp))
                Text(text = "${item.age}",modifier = Modifier.padding(start = 16.dp))

            }
        }
    }
}

@Composable
fun ShowLazyRow(allPersonModel: List<PersonModel>){
    LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(8.dp)){
        items(allPersonModel){item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clip(CircleShape)
                        .background(color = item.color)
                )
                Text(text = item.name, color = item.color, fontWeight = FontWeight.Bold)
                Text(text = "${item.age}")

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val allPersons = listOf<PersonModel>(
        PersonModel(Color.Red,"Abdullah",16),
        PersonModel(Color.Blue,"Mehmet",35),
        PersonModel(Color.Cyan,"Ali",45),
        PersonModel(Color.Green,"Sude",85),
        PersonModel(Color.Yellow,"Süleyman",17),
        PersonModel(Color.Magenta,"Serdar",13),
        PersonModel(Color.Cyan,"Serpil",28),
        PersonModel(Color.Blue,"Seda",24),
        PersonModel(Color.Black,"Rukiye",23),
        PersonModel(Color.Cyan,"Ali",45),
        PersonModel(Color.Green,"Sude",85),
        PersonModel(Color.Yellow,"Süleyman",17),
        PersonModel(Color.Magenta,"Serdar",13),
        PersonModel(Color.Cyan,"Serpil",28),
        PersonModel(Color.Blue,"Seda",24),
        PersonModel(Color.Black,"Rukiye",23),
    )
    JatpackComposeTheme {
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {
            ShowLazyRow(allPersons)
        }


    }


}
