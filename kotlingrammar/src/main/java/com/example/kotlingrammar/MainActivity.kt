/**
1. **Kotlin Basics**
   - Syntax and Basic Constructs: Understanding the basic syntax, variables, data types, and control flow structures (if-else, when, loops).
   - Functions: Writing basic functions, function parameters, and return types.
   - Null Safety: Understanding Kotlin's null safety features, including nullable types and safe calls (`?.`).

2. **Object-Oriented Programming in Kotlin**
   - Classes and Objects: Understanding the basics of classes, objects, constructors, and initialization blocks.
   - Inheritance: Concepts of inheritance, superclass, subclass, and overriding methods.
   - Interfaces and Abstract Classes: Understanding how to define and implement interfaces, and the role of abstract classes.

3. **Data Classes and Collections**
   - Data Classes: Learning about data classes and their benefits in Kotlin for holding data.
   - Collections: Understanding various collection types (List, Set, Map) and their usage.

4. **Kotlin Standard Library Functions**
   - Working with common library functions for collections like `map`, `filter`, `forEach`, etc.
   - String manipulation and regular expressions.

5. **Lambdas and Higher-Order Functions**
   - Understanding lambdas, method references, and higher-order functions.
   - Using functional programming concepts to write more concise and expressive code.

6. **Coroutines and Asynchronous Programming**
   - Basics of coroutines for managing background tasks and asynchronous operations.
   - Understanding suspend functions, CoroutineScope, and basic coroutine builders like `launch` and `async`.

7. **Kotlin Extensions**
   - Writing extension functions and properties to add functionality to existing classes.

8. **Kotlin Type System**
   - Understanding type inference, explicit typing, and Kotlin's type system.
   - Variance in type parameters (invariance, covariance, contravariance).

9. **Best Practices and Conventions**
   - Writing idiomatic Kotlin code.
   - Understanding Kotlin's coding conventions and best practices.

10. **Interoperability with Java**
    - Calling Java code from Kotlin and vice versa.
    - Understanding the interoperability between Java and Kotlin, including handling nullability.

*/

package com.example.kotlingrammar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlingrammar.ui.theme.ComposeMasterTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMasterTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("kotlinBasics") { KotlinBasicsExamples() }
                    composable("kotlinObjectOriented"){ KotlinObjectOrientedExamples()}
                    composable("kotlinDataClassesAndCollections"){ KotlinDataClassesAndCollectionsExamples()}
                    composable("kotlinStandardLibrary"){KotlinStandardLibraryExamples()}
                    composable("kotlinLambdasAndHigherOrderFunctions"){LambdasAndHigherOrderFunctionsExamples()}
                    composable("kotlinCoroutinesAndAsync"){KotlinCoroutinesAndAsyncExamples()}
                    composable("kotlinExtensions"){KotlinExtensionsExamples()}
                    composable("kotlinTypeSystem"){KotlinTypeSystemExamples()}
                    composable("kotlinBestPractice"){KotlinBestPracticeExample()}
                    composable("kotlinJavaInteroperability"){KotlinJavaInteroperabilityExample()}
                }
            }
        }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // Define a consistent button size
        val buttonModifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 4.dp)
        Button(onClick = { navController.navigate("kotlinBasics") }) {
            Text("Kotlin Basics")
        }
        Button(onClick = { navController.navigate("kotlinObjectOriented") }) {
            Text("Object-Oriented")
        }
        Button(onClick = { navController.navigate("kotlinDataClassesAndCollections") }) {
            Text("Data Classes and Collections")
        }
        Button(onClick = { navController.navigate("kotlinStandardLibrary") }) {
            Text("Standard Library")
        }
        Button(onClick = { navController.navigate("kotlinLambdasAndHigherOrderFunctions") }) {
            Text("Lambdas and Higher Order Functions")
        }
        Button(onClick = { navController.navigate("kotlinCoroutinesAndAsync") }) {
            Text("Coroutines and Asynchronous")
        }
        Button(onClick = { navController.navigate("kotlinExtensions") }) {
            Text("Extensions")
        }
        Button(onClick = { navController.navigate("kotlinTypeSystem") }) {
            Text("Type System")
        }
        Button(onClick = { navController.navigate("kotlinBestPractice") }) {
            Text("Best Practice")
        }
        Button(onClick = { navController.navigate("kotlinJavaInteroperability") }) {
            Text("Java Interoperability")
        }
    }
}

/**
 * # Kotlin Basics
## Variables (Immutable and Mutable)
- val for immutable variables.
- var for mutable variables.

## Basic Data Types
- Integer (Int), floating-point (Double), boolean (Boolean), character (Char), string (String).

## Control Structures
- if-else for conditional logic.
- when as a versatile replacement for switch-case.
- Loops: for (iterate over ranges), while (loop with a condition).

## Functions
- Declaring functions (fun), parameters, return types.
- Default parameters in functions.

## Data Classes
- Creating and using data classes.

## Nullable Types and Null Safety
- Using nullable types (String?) and null safety operators (?:).

## Composable Functions and UI
- Building a UI with Composables to demonstrate these concepts.

## Preview Annotation
- Previewing Composables with @Preview.
 */
@Composable
fun KotlinBasicsExamples() {
    Column {
        // Immutable variable
        val greeting: String = "Hello"
        Text("$greeting Jetpack Compose!")

        // Mutable variable
        var clickCount by remember { mutableStateOf(0) }
        Button(onClick = { clickCount++ }) {
            Text("Clicked $clickCount times")
        }

        // Data Types and Control Structures
        displayDataTypesAndControlStructures()

        // Functions and Default Parameters
        Text(greetPerson("Alice"))
        Text(greetPerson("Bob", age = 28))

        // Using a Data Class
        val user = User("John", "Doe")
        Text("User: ${user.firstName} ${user.lastName}")

        // Nullable Types and Null Safety
        val nullableName: String? = null
        Text(nullableName ?: "No Name") // Elvis Operator
    }
}

// Function to demonstrate data types and control structures
fun displayDataTypesAndControlStructures() {
    // Basic Data Types
    val myInt: Int = 10
    val myDouble: Double = 10.5
    val myBoolean: Boolean = true
    val myChar: Char = 'A'
    val myString: String = "Kotlin"

    // Control Structure: If-Else
    val message = if (myInt > 5) "Greater than 5" else "Not greater than 5"

    // Control Structure: When
    val whenResult = when (myInt) {
        in 1..5 -> "Between 1 and 5"
        else -> "Outside range"
    }

    // Control Structure: Loop - For
    for (i in 1..3) {
        println("Loop iteration: $i")
    }

    // Control Structure: Loop - While
    var count = 3
    while (count > 0) {
        println("Countdown: $count")
        count--
    }
}

fun greetPerson(name: String, age: Int = 25): String {
    return "Hello, $name! Age: $age"
}

data class User(val firstName: String, val lastName: String)

@Preview(showBackground = true)
@Composable
fun KotlinBasicsExamplesPreview() {
    ComposeMasterTheme {
        KotlinBasicsExamples()
    }
}


/**
 * # Object Oriented
## Classes and Objects
- Define a simple Person class with properties and initialize an object of this class.
- Demonstrate object instantiation and property access.

## Inheritance
- Create a Student class that inherits from Person. Discuss how subclassing works and how to call the superclass constructor.
- Introduce the concept of adding additional properties in the subclass (major in Student).

## Interfaces and Abstract Classes
- Define a Shape interface with a method calculateArea.
- Implement the Shape interface in a Circle class. Discuss method overriding and concrete implementation.
- Demonstrate polymorphism and the use of interfaces to define common behavior.

## Composable Functions and UI Integration
- Use Composables to display information about instances of these classes and interfaces.
- Highlight how object-oriented concepts are applicable in building Android UIs with Jetpack Compose.
 */

@Composable
fun KotlinObjectOrientedExamples() {
    Column {
        // Working with Classes and Objects
        val person = Person("Alice", 30)
        Text("Person: ${person.name}, Age: ${person.age}")

        // Inheritance
        val student = Student("Bob", 20, "Mathematics")
        Text("Student: ${student.name}, Age: ${student.age}, Major: ${student.major}")

        // Interfaces and Abstract Classes
        val circle = Circle(4.5)
        Text("Circle area: ${circle.calculateArea()}")
    }
}

// Simple class
open class Person(val name: String, val age: Int)

// Inheritance: Student is a subclass of Person
class Student(name: String, age: Int, val major: String) : Person(name, age)

// Interface for shapes
interface Shape {
    fun calculateArea(): Double
}

// Implementing an interface
class Circle(private val radius: Double) : Shape {
    override fun calculateArea(): Double {
        return Math.PI * radius * radius
    }
}


/**
 * # Data Class and Collections
## Data Classes
- Introduce the concept of data classes in Kotlin, which are used for holding data.
- Show how to declare a data class (data class Book) and discuss the benefits like automatically generated equals(), hashCode(), and toString() methods.
- Create an instance of the Book data class and display its properties.

## Collections
- Lists: Explain how to create a list using listOf and demonstrate its use. Lists maintain the order of elements and can contain duplicates.
- Sets: Use setOf to create a set and discuss how sets ensure unique elements and do not maintain the order.
- Maps: Introduce maps using mapOf, which hold data in key-value pairs. Show how to create a map and access its elements.

## Composable Functions and UI Integration
- Use Composables to display the properties of the data class and the contents of the collections.
- Emphasize how Kotlin's collection operations can be utilized in Android app development, particularly with Jetpack Compose.
 */
@Composable
fun KotlinDataClassesAndCollectionsExamples() {
    Column {
        // Using a Data Class
        val book = Book("Kotlin Programming", "John Doe", 2021)
        Text("Book: ${book.title} by ${book.author}, published in ${book.year}")

        // Collections: List, Set, Map
        val numbers = listOf(1, 2, 3, 4, 5)
        Text("List: $numbers")

        val uniqueNumbers = setOf(1, 2, 2, 3, 4, 5)
        Text("Set (Unique Numbers): $uniqueNumbers")

        val nameToAge = mapOf("Alice" to 30, "Bob" to 25)
        Text("Map (Name to Age): $nameToAge")
    }
}

// Data class example
data class Book(val title: String, val author: String, val year: Int)


/**
 * # Standard Library
## Collection Operations
- Demonstrate how to use standard library functions like map and filter on collections.
- map is used to apply a transformation to each element in a list.
- filter is used to select elements based on a condition.
- 'sortedDescending' to sort a collection in descending order.
- Usage of ranges (1..10) and checking if a value is within a range (in keyword).
- 'map' to transform each element in a collection.
- 'filter' to select elements based on a condition.
- 'forEach' to perform an action for each element.

## Sorted and Ranges
- 'sortedDescending' to sort the collection in descending order.
- Usage of ranges (1..10) and checking if a value is within a range ('in' keyword).

## Utility Functions
- 'maxOrNull' to find the maximum element in a collection.
- 'reduce' to accumulate a value starting from the first element and applying an operation (e.g., summing all elements).
- 'maxOrNull' to find the maximum element in a collection.
- 'reduce' to accumulate a value starting from the first element and applying an operation (e.g., summing all elements).

## String Manipulation
- Show basic string operations like converting to uppercase (uppercase()).
- Discuss other string functions like replace, substring, etc.
- 'substring' to extract a portion of a string.

## Regular Expressions
- Introduce basic usage of regular expressions.
- Use Regex to check if a string matches a specific pattern.

## Composable Functions and UI Integration
- Use Composables to display the results of these operations, illustrating how Kotlin's standard library functions can be effectively used in Android development.
 */
@Composable
fun KotlinStandardLibraryExamples() {
    Column {
        // Collection Operations
        val numbers = listOf(1, 2, 3, 4, 5)
        Text("Original List: $numbers")

        // map operation
        val doubled = numbers.map { it * 2 }
        Text("Doubled List (map): $doubled")

        // filter operation
        val evenNumbers = numbers.filter { it % 2 == 0 }
        Text("Even Numbers (filter): $evenNumbers")

        // forEach operation
        var forEachResult = ""
        numbers.forEach { forEachResult += "$it;" }
        Text("ForEach Result: $forEachResult")

        // More Collection Operations
        val sortedNumbers = numbers.sortedDescending()
        Text("Sorted Descending: $sortedNumbers")

        // String Manipulation
        val originalString = "Kotlin Programming"
        Text("Original String: $originalString")
        val upperString = originalString.uppercase()
        Text("Uppercase String: $upperString")
        val substring = originalString.substring(0, 6)
        Text("Substring (0-6): $substring")

        // Regular Expressions
        val regex = Regex("[a-zA-Z]+")
        val containsOnlyLetters = regex.matches(originalString)
        Text("Contains Only Letters: $containsOnlyLetters")

        // Utility Functions
        val range = 1..10
        Text("Range: $range")
        val isInRange = 5 in range
        Text("Is 5 in Range: $isInRange")
        val maxNumber = numbers.maxOrNull()
        Text("Max Number: $maxNumber")
        val total = numbers.reduce { sum, element -> sum + element }
        Text("Sum of Numbers: $total")
    }
}

/**
 * # Lambdas and Higher Order Functions
## Lambdas
- Explain what lambdas are (anonymous functions) and show how they are used, for example with the map function.
- Demonstrate the concise syntax of lambdas in Kotlin.

## Higher-Order Functions
- Introduce the concept of higher-order functions, which can take functions as parameters or return a function.
- Show an example of a higher-order function (calculate) that takes a function as a parameter.
- Demonstrate both passing a regular function (sum) and a lambda as arguments to a higher-order function.

## Method References
- Explain method references (::) used as shorthand for functions where only a single method is called.
- Example with sortedWith and compareByDescending.
 */
@Composable
fun LambdasAndHigherOrderFunctionsExamples() {
    Column {
        // Lambdas
        val numbers = listOf(1, 2, 3, 4, 5)
        Text("Original List: $numbers")

        // Using lambdas with map
        val doubled = numbers.map { it * 2 }
        Text("Doubled List: $doubled")

        // Higher-Order Functions
        val result = calculate(10, 5, ::sum)
        Text("Sum of 10 and 5: $result")

        // Passing lambda to a higher-order function
        val multiplied = calculate(10, 5) { a, b -> a * b }
        Text("Multiplication of 10 and 5: $multiplied")

        // Method References
        val sortedDescending = numbers.sortedWith(compareByDescending { it })
        Text("Sorted Descending: $sortedDescending")
    }
}

// Example of a higher-order function
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

// Example of a simple function used as a method reference
fun sum(a: Int, b: Int) = a + b


/**
 * # Coroutines and Asynchronous
## Basics of Coroutines
- Explain what coroutines are and how they simplify asynchronous programming in Kotlin.
- Introduce the concept of CoroutineScope and different dispatchers like Dispatchers.Main.

## Suspend Functions
- Discuss suspend functions, which are the building blocks of coroutines, allowing for non-blocking asynchronous operations.
- Show an example of a suspend function (fetchData) that simulates a delayed operation.

## Coroutine Builders: launch and async
- Use the launch coroutine builder to start a new coroutine in the UI context.
- Explain the difference between launch (fire-and-forget) and async (which returns a Deferred for result retrieval).

## Integration with UI
- Demonstrate how coroutines can be used in a Compose UI to perform tasks without blocking the main thread.
- Use a Button to trigger the coroutine and a Text to display the result.
 */
@Composable
fun KotlinCoroutinesAndAsyncExamples() {
    val coroutineResult = remember { mutableStateOf("") }

    Column {
        Button(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                coroutineResult.value = "Loading..."
                val data = fetchData()
                coroutineResult.value = data
            }
        }) {
            Text("Fetch Data")
        }

        Text(coroutineResult.value)
    }
}

suspend fun fetchData(): String {
    delay(2000) // Simulating a network call
    return "Data fetched successfully"
}

/**
 * # Kotlin Extensions
## Extension Functions
- Introduce the concept of extension functions in Kotlin.
- Demonstrate how to add new functionality (like reverseString()) to an existing class (String) without modifying its source code.

## Extension Properties
- Explain extension properties and how they can be used to add new properties to existing classes.
- Show an example of adding an isEven property to the Int class.

## Practical Use in UI
- Utilize these extensions in the Compose UI to demonstrate their practicality.
- Highlight how extensions can lead to more readable and maintainable code.
 */
@Composable
fun KotlinExtensionsExamples() {
    Column {
        // Using an extension function
        val originalText = "Hello, Kotlin!"
        val reversedText = originalText.reverseString()
        Text("Original: $originalText")
        Text("Reversed: $reversedText")

        // Using an extension property
        val number = 42
        Text("Number: $number, Is Even: ${number.isEven}")
    }
}

// Extension function to String class
fun String.reverseString(): String {
    return this.reversed()
}

// Extension property to Int class
val Int.isEven: Boolean
    get() = this % 2 == 0


/**
 * # Kotlin Type System
## Type Inference
- Explain how Kotlin can infer types from the context, leading to less verbose code.
- Use examples to show type inference in action (inferredType variable).

## Explicit Typing
- Discuss scenarios where explicit typing is necessary or preferable for clarity or restrictiveness (explicitType variable).

## Variance in Type Parameters
- Covariance: Explain how a List<String> can be assigned to a List<Any> due to its covariant nature. Use the List example to illustrate covariance.
- Contravariance:Use a Consumer<String> to demonstrate contravariance. Explain how a Consumer<String> can be used where a Consumer<Any> is expected.

## Integration with UI
- Display these types and their behaviors in the Compose UI to visualize the concepts.
 */
@Composable
fun KotlinTypeSystemExamples() {
    Column {
        // Type Inference
        val inferredType = "Inferred as String"
        Text("Type Inferred: $inferredType")

        // Explicit Typing
        val explicitType: Number = 42
        Text("Explicit Type: $explicitType")

        // Variance - Example with List (covariant)
        val stringList: List<String> = listOf("Hello", "World")
        val anyList: List<Any> = stringList // Covariance
        Text("Covariant List: $anyList")

        // Contravariance - Example with a consumer functional interface
        val anyConsumer: Consumer<Any> = object : Consumer<Any> {
            override fun accept(value: Any) {
                println("Accepting value: $value")
            }
        }
        val stringConsumer: Consumer<String> = anyConsumer // Contravariance
        Text("Contravariant Consumer: Any Consumer used for String")
    }
}

// Contravariant functional interface
interface Consumer<in T> {
    fun accept(value: T)
}


/**
 * # Kotlin Best Practice
## Using Scope Functions (apply, let, etc.)
- apply is used for configuring objects. It returns the object itself after applying the block.
- let is useful for null-checking and executing code blocks only if the object is not null.

## Chaining Collection Operations
- Demonstrate Kotlin's ability to chain operations like filter and map for concise and readable collection processing.

## Adhering to Kotlin Coding Conventions
- These examples follow Kotlin's coding conventions and idiomatic practices, such as using explicit names, avoiding unnecessary verbosity, and leveraging Kotlin's standard library functions.

## Null Safety
- Illustrate Kotlin's approach to null safety using safe calls (?.) and the Elvis operator (?:).
 */
@Composable
fun KotlinBestPracticeExample() {
    Column {
        // Example of using 'apply' for object configuration
        val textViewConfiguration = TextViewConfiguration().apply {
            textSize = 16
            textColor = "Black"
        }
        Text("TextView: Size=${textViewConfiguration.textSize}, Color=${textViewConfiguration.textColor}")

        // Example of using 'let' for null-checking and transformation
        val nullableString: String? = "Kotlin"
        nullableString?.let {
            Text("Nullable String (let): $it")
        }

        // Example of Collection Operations with chaining
        val numbers = listOf(1, 2, 3, 4, 5)
        val processedNumbers = numbers
            .filter { it % 2 == 0 }
            .map { it * it }
        Text("Processed Numbers: $processedNumbers")

        // Using Elvis Operator for providing a default value
        val nullableText: String? = null
        val textToShow = nullableText ?: "Default Text"
        Text("Text with Elvis Operator: $textToShow")
    }
}

class TextViewConfiguration {
    var textSize: Int = 0
    var textColor: String = ""
}



/**
 * # Java Interoperability
## Calling Java from Kotlin
- Demonstrate how to call a static Java method (getGreeting) from Kotlin, showcasing the seamless interoperability.

## Handling Nullability
- Illustrate handling potential null values returned from Java methods in Kotlin. Java's platform types are treated specially in Kotlin, allowing Kotlin to handle Java's nullability.
- Use the Elvis operator (?:) to provide a default value in case the Java method returns null.
 */
@Composable
fun KotlinJavaInteroperabilityExample() {
    Column {
        // Calling a Java method from Kotlin
        val javaGreeting = JavaExample.getGreeting()
        Text(javaGreeting)

        // Handling nullability from Java methods
        val nullableText = JavaExample.nullableFunction() ?: "Default value from Java"
        Text(nullableText)
    }
}
