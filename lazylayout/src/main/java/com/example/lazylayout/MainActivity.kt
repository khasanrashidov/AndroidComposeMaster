package com.example.lazylayout


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lazylayout.ui.theme.ComposeMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}




enum class LayoutType {
    Column, Row, Grid, HorizontalGrid
}

@Composable
fun MyApp() {
    var layoutType by remember { mutableStateOf(LayoutType.Column) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(getLayoutTitle(layoutType)) },
                backgroundColor = MaterialTheme.colors.primary,
                actions = {
                    Button(
                        onClick = { layoutType = getNextLayoutType(layoutType) },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(getNextLayoutTitle(layoutType))
                    }
                }
            )
        },
        content = { paddingValues ->
            when (layoutType) {
                LayoutType.Column -> {
                    LazyColumnExample(
                        items = List(100) { "Item $it" },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
                LayoutType.Row -> {
                    LazyRowExample(
                        items = List(100) { "Item $it" },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
                LayoutType.Grid -> {
                    LazyVerticalGridExample(
                        items = List(100) { "Item $it" },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
                LayoutType.HorizontalGrid -> {
                    LazyHorizontalGridExample(
                        items = List(100) { "Item $it" },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    )
}

@Composable
fun LazyColumnExample(items: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.LightGray)
            ) {
                Text(text = item)
            }
        }
    }
}

@Composable
fun LazyRowExample(items: List<String>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier.fillMaxSize()) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                backgroundColor = Color.LightGray
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LazyVerticalGridExample(items: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier.fillMaxSize()) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.LightGray)
            ) {
                Text(text = item)
            }
        }
    }
}

@Composable
fun LazyHorizontalGridExample(items: List<String>, modifier: Modifier = Modifier) {
    LazyHorizontalGrid(rows = GridCells.Fixed(3), modifier = modifier.fillMaxSize()) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.LightGray)
            ) {
                Text(text = item)
            }
        }
    }
}

fun getLayoutTitle(layoutType: LayoutType): String {
    return when (layoutType) {
        LayoutType.Column -> "LazyColumn Example"
        LayoutType.Row -> "LazyRow Example"
        LayoutType.Grid -> "LazyVerticalGrid Example"
        LayoutType.HorizontalGrid -> "LazyHorizontalGrid Example"
    }
}

fun getNextLayoutType(layoutType: LayoutType): LayoutType {
    return when (layoutType) {
        LayoutType.Column -> LayoutType.Row
        LayoutType.Row -> LayoutType.Grid
        LayoutType.Grid -> LayoutType.HorizontalGrid
        LayoutType.HorizontalGrid -> LayoutType.Column
    }
}

fun getNextLayoutTitle(layoutType: LayoutType): String {
    return when (layoutType) {
        LayoutType.Column -> "Switch to LazyRow"
        LayoutType.Row -> "Switch to LazyVerticalGrid"
        LayoutType.Grid -> "Switch to LazyHorizontalGrid"
        LayoutType.HorizontalGrid -> "Switch to LazyColumn"
    }
}

