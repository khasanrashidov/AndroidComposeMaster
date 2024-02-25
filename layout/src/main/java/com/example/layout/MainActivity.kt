package com.example.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.layout.ui.theme.ComposeMasterTheme

/**
 * # Jetpack Compose UI Elements
 *
 * https://developer.android.com/jetpack/compose/layouts
 *
 * - *Text:* Used to display static or dynamic text content.
 *
 * - *TextField:* Provides a text input field for users to enter text.
 *
 * - *Button:* A clickable UI element to trigger actions or navigate to other screens.
 *
 * - *Image:* Displays images or icons in the UI.
 *
 * - *Icon:* Renders a vector-based icon.
 *
 * - *Checkbox:* Allows users to select or deselect options.
 *
 * - *RadioButton:* Provides a list of mutually exclusive options.
 *
 * - *Switch:* A toggle switch for on/off or true/false settings.
 *
 * - *Slider:* Allows users to select a value within a range by dragging a slider handle.
 *
 * - *ProgressBar:* Displays progress feedback to users.
 *
 * - *Divider:* Adds a horizontal or vertical line to separate content.
 *
 * - *Spacer:* Adds spacing between UI elements.
 *
 * - *Surface:* A container for other UI elements with a background color.
 *
 * - *Card:* Displays content with a card-like design, including elevation and rounded corners.
 *
 * - *Dialog:* Used to create dialogs or pop-up windows.
 *
 * - *Drawer:* A sliding panel that appears from the edge of the screen.
 *
 * - *TabBar:* A tab-based navigation component for switching between different screens or sections.
 *
 * - *TopAppBar:* A top app bar that typically contains app-related actions and navigation.
 *
 * - *BottomAppBar:* A bottom app bar for actions like navigation and contextual actions.
 *
 * - *Snackbar:* Displays a brief message at the bottom of the screen.
 *
 * - *ModalBottomSheet:* A bottom sheet that can be used for displaying additional content or options.
 *
 * - *LazyColumn:* A vertically scrolling list of items with lazy loading.
 *
 * - *LazyRow:* A horizontally scrolling list of items with lazy loading.
 *
 * - *LazyGrid:* A grid layout with lazy loading of items.
 *
 * - *Navigation:* Components and navigation actions for creating navigation flows.
 *
 * - *Compose Navigation Drawer:* A navigation drawer for creating navigation menus.
 *
 * - *ConstraintLayout:* A layout system for creating complex and responsive UIs.
 *
 * - *Scaffold:* *TopAppBar*, *BottomAppBar*, *FloatingActionButton (FAB)*, *DrawerContent*, *Content*
 */
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
        topBar = { TopAppBar(title = { Text("Layout Tutorial") }) },
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
            .background(color = MaterialTheme.colorScheme.surface)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMasterTheme {
        MainScreen()
    }
}