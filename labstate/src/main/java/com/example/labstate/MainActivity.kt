package com.example.labstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labstate.ui.theme.ComposeMasterTheme

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
    // Mutable states for name, ID number, and photo shape
    var name by remember { mutableStateOf("Hong Jeong") }
    var idNumber by remember { mutableStateOf("123456") }
    var isCircleShape by remember { mutableStateOf(true) } // True for circle, false for square

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
        "Quality Assurance Engineer"
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
            ) {
                val imageShape = if (isCircleShape) CircleShape else RoundedCornerShape(0.dp)

                Image(
                    painter = painterResource(id = R.drawable.hongjeong), // Make sure this drawable exists
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(imageShape)
                        .border(1.dp, MaterialTheme.colorScheme.primary, imageShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = name, fontSize = 20.sp)
                    Text(text = idNumber, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Button to toggle the shape
            Button(onClick = { isCircleShape = !isCircleShape }) {
                Text(if (isCircleShape) "Switch to Square" else "Switch to Circle")
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Buttons to change the state values
            Button(onClick = { name = "Jeong Hong" }) {
                Text("Change Name")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { idNumber = "654321" }) {
                Text("Change ID Number")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Adding a LazyColumn to display the list of careers
            Text("Careers:", style = MaterialTheme.typography.titleLarge)
            LazyColumn {
                items(careers.size) { index ->
                    Text(text = careers[index], style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
