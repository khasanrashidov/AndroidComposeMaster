package com.example.labanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labanimation.ui.theme.ComposeMasterTheme

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
                    NameCard()
                }

            }
        }
    }
}



@Composable
fun NameCard() {
    var imageToggle by remember { mutableStateOf(true) }
    val imageResource = if (imageToggle) R.drawable.hongjeong1 else R.drawable.hongjeong2

    var expanded by remember { mutableStateOf(true) }
    val imageSize = 200.dp //by animateDpAsState(targetValue = if (expanded) 200.dp else 100.dp)

    val infiniteTransition = rememberInfiniteTransition(label= "infinite transition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )


    // List of careers
    val careers = listOf(
        "Software Engineer",
        "Data Scientist",
        "Product Manager",
        "Graphic Designer",
        "UX Designer",
        "Project Manager",
        "Web Developer",
        "Mobile Developer",
        "Systems Analyst",
        "IT Consultant",
        "Network Administrator",
        "Database Administrator",
        "Security Analyst",
        "Cloud Architect",
        "DevOps Engineer",
        "Quality Assurance Engineer",
        "Software Engineer",
        "Data Scientist",
        "Product Manager",
        "Graphic Designer"
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .clickable { imageToggle = !imageToggle } // Toggle image on click
            ) {
                val imageShape = if (imageToggle) RoundedCornerShape(1.dp) else CircleShape

                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(imageSize)
                        .clip(imageShape)
                        .border(1.dp, MaterialTheme.colorScheme.primary, imageShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Name: Hong Jeong", fontSize = 20.sp, color = color)
                    Text(text = "ID: 1234567890", fontSize = 20.sp, color = color)
                    Text(text = "Tel: (998) 99 123 4567", fontSize = 20.sp, color = color)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (expanded) "Careers" else "Careers",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.clickable { expanded = !expanded }
            )
            Box(modifier = Modifier.weight(1f)) {
                // Animate the content size of the LazyColumn
                if (expanded) {
                    LazyColumn(modifier = Modifier.animateContentSize()) {
                        items(careers.size) { index ->
                            Text(
                                text = careers[index],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
