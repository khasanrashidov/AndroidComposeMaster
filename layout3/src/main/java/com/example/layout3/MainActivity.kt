package com.example.layout3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layout3.ui.theme.ComposeMasterTheme

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
                    PhotoGridScreen()
                }
            }
        }
    }
}

@Composable
fun PhotoGridScreen() {
    // Replace with your drawable images
    val images = listOf(
        R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
        R.drawable.photo4, R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
        R.drawable.photo4, R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
        R.drawable.photo4
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(images) { image ->
            PhotoItem(painterResource(id = image))
        }
    }
}

@Composable
fun PhotoItem(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = null, // Provide proper content description for accessibility
        modifier = Modifier
            .size(width = 150.dp, height = 150.dp) // Set a fixed size for each image
            .padding(2.dp), // Optional: Add padding if needed
        contentScale = ContentScale.Crop // Crop the image to fit the size
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        PhotoGridScreen()
    }
}