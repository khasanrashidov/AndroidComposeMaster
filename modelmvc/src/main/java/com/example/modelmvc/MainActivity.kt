package com.example.modelmvc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modelmvc.ui.theme.ComposeMasterTheme

/**
 * # Models
 *
 * [Model Architectures](https://www.youtube.com/watch?v=I5c7fBgvkNY
 * | Feature/Pattern | MVC                | MVP                  | MVVM                        | MVI                        | Additional Notes                            |
 * |-----------------|--------------------|----------------------|-----------------------------|----------------------------|--------------------------------------------|
 * | Main Components | Model, View, Controller | Model, View, Presenter | Model, View, ViewModel       | Model, View, Intent        | -                                          |
 * | Data Flow       | Bidirectional      | Mostly unidirectional | Data binding (two-way)      | Unidirectional             | MVI strictly follows unidirectional flow.  |
 * | User Input      | Controller         | Presenter            | ViewModel (via Commands)    | Intent                     | -                                          |
 * | Data Binding    | Manual             | Manual               | Automatic/Declarative       | Manual or using a framework| MVVM promotes automatic data binding.      |
 * | State Management| Controller/Model   | Presenter            | ViewModel                   | Entire cycle (M -> V -> I) | MVI treats state as an immutable object.   |
 * | Use Case        | Web applications   | Desktop/mobile applications | Desktop/mobile applications | Reactive applications       | -                                          |
 * | Testing         | Moderate          | Easier than MVC      | Easier due to data binding  | Easier due to clear flow   | MVVM and MVI facilitate better unit testing.|
 * | Popularity      | Traditional        | Was popular in early 2000s | High in modern applications| Emerging in reactive programming | MVVM is popular in frameworks like Angular, React.|
 *
 */

class MainActivity : ComponentActivity() {
    private val counterModel = CounterModel(0)
    private val counterController = CounterController(counterModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    CounterScreen(model = counterModel, controller = counterController)
                }
            }
        }
    }
}
