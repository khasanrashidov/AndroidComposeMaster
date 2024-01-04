package com.example.androidcomposelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcomposelayout.ui.theme.ComposeMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("AndroidComposeLayout Tutorial") }) },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), color = MaterialTheme.colorScheme.background
            ) {
                GridList()
            }
        }
    )
}

@Composable
fun GridList() {
    val gridData = List(10) { "Item $it" } // Sample grid data

    LazyColumn {
        items(gridData.chunked(3)) { rowItems -> // Change 3 to the number of columns you want
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(rowItems) { item ->
                    GridItem(item)
                }
            }
        }
    }
}

@Composable
fun GridItem(item: String) {
    val cardElevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp,
        pressedElevation = 8.dp,
        disabledElevation = 0.dp
    )
    Card(
        elevation = cardElevation,
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .background(color=MaterialTheme.colorScheme.surface)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun AndroidComposeLayoutTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme =  lightColorScheme(primary = Color.Blue, secondary = Color.Green),
//        typography = Typography(defaultFontFamily = FontFamily.SansSerif),
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(0.dp)
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        MainScreen()
    }
}