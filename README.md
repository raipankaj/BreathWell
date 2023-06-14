# BreathWell
A sample app which will be used for showcasing how to create Android app with Jetpack Compose.

<h3>Jetpack Compose</h3>
Jetpack Compose allows developers to build UIs using a reactive and composable programming model. Instead of using traditional XML-based layouts or the older View-based system, Compose allows developers to define the user interface using Kotlin code. With Compose, UI elements are described as functions or composables that can be combined and reused to create complex UI hierarchies.
</br>
</br>One of the key advantages of Jetpack Compose is its simplicity and productivity. The declarative nature of Compose makes it easier to understand, maintain, and modify UI code compared to the imperative and XML-based approaches. It also provides a live preview feature that allows developers to see the changes in the UI in real-time as they write the code.
</br></br>
<h3>ViewModel</h3>
ViewModel is an architectural component provided by the Android Jetpack library. It is designed to store and manage UI-related data in a lifecycle-aware manner. ViewModel helps separate the concerns of the user interface (UI) and the underlying data, improving the overall architecture of Android applications.
</br></br>
<h3>Repo</h3>
In Android, the repository is a component of the Model layer in the recommended architecture patterns like Model-View-ViewModel (MVVM) or Model-View-Presenter (MVP). It acts as an intermediary between the data sources (such as a local database, network API, or cache) and the rest of the application components, such as ViewModel or Presenter.
</br></br>
<h3>Data Source</h3>
In the context of Android development and software architecture, a data source refers to the origin or location of data that an application interacts with. It can be any storage or retrieval mechanism that provides the application with the necessary data.

<h2>Sample App</h2>
As the objective is just to demonstrate the functioning of each layer hence a dummy data is prepared which in reality for production app could be the original source of data from local or cloud.
  
</br></br>
FakeDataSource - It provides the data for the display
</br>
BreathRepo - It fetch the data from the data source and provide it to the viewmodel, it basically have just two methods
```kotlin
object BreathRepo {

    fun getAllBreathingExercises() = FakeDataSource.breathList

    fun getBreathingExerciseBasedOnType(type: String?) = FakeDataSource.getBasedOnType(type)

}
```
</br>
BreathViewModel - It fetch the data from the BreathRepo and provides it to the UI elements created through the Compose UI
</br>
Dashboard (Composable) - It shows the list of all breathing exerices available
</br>
ScheduleTimer (Composable) - It shows the countdown timer associated for the breathing exerices selected from the dashboard ui

</br></br>
<h2>Codebase</h2>
<h2>Codebase Documentation: Dashboard Composable and Related Functions</h2>

<h3>Dashboard Composable</h3>

<p><strong>Overview</strong></p>
<p>The <code>Dashboard</code> Composable function represents the main UI component for the dashboard screen. It displays a list of breath exercise tiles and a top app bar. It receives a <code>BreathViewModel</code> instance and a callback function for handling click events on the exercise tiles.</p>

<p><strong>Parameters</strong></p>
<ul>
  <li><code>breathViewModel</code>: An instance of <code>BreathViewModel</code> that provides access to the breath exercise data and related operations.</li>
  <li><code>onClick</code>: A callback function that takes a <code>String</code> parameter representing the label of the clicked exercise tile.</li>
</ul>

<p><strong>Usage</strong></p>
<p>To use the <code>Dashboard</code> Composable, simply call it from your UI code and pass the required parameters. For example:</p>

<pre><code>Dashboard(breathViewModel = breathViewModel, onClick = { label -&gt;
    // Handle click event here
})
</code></pre>

<p><strong>Functionality</strong></p>
<ol>
  <li>The Composable function collects a list of exercise types using the <code>exerciseTypes</code> flow from the provided <code>BreathViewModel</code>.</li>
  <li>It launches an effect using <code>LaunchedEffect</code> to fetch the list of all exercises by calling <code>getListOfAllExercises()</code> on the <code>breathViewModel</code>.</li>
  <li>The UI is built using the <code>Scaffold</code> composable from the Jetpack Compose Material library.</li>
  <li>The top app bar is created using the <code>TopAppBar</code> composable and displays the title "BreathWell".</li>
  <li>The content area of the screen contains a <code>Box</code> composable that wraps the <code>BreathExerciseList</code>.</li>
  <li>The <code>BreathExerciseList</code> composable is responsible for rendering the list of breath exercise tiles.</li>
  <li>The <code>BreathExerciseList</code> receives the list of breath exercise types (<code>types</code>) and the <code>onClick</code> callback function.</li>
  <li>The list of breath exercises is displayed using <code>LazyVerticalGrid</code>, a composable that creates a grid layout with a fixed number of columns and lazily composes only the visible items for efficiency.</li>
  <li>Each item in the <code>types</code> list is mapped to a <code>BreathTypeTile</code> composable, which represents an individual breath exercise tile.</li>
  <li>The <code>BreathTypeTile</code> composable displays a card with an image and label for each exercise type.</li>
  <li>Clicking on a <code>BreathTypeTile</code> triggers the provided <code>onClick</code> callback function, passing the label of the clicked exercise tile.</li>
</ol>

<h3>BreathExerciseList Composable</h3>

<p><strong>Overview</strong></p>

<p>The <code>BreathExerciseList</code> Composable is responsible for rendering the list of breath exercise tiles. It receives a list of <code>Breath</code> objects representing the exercise types and a callback function for handling click events on the exercise tiles.</p>

<p><strong>Parameters</strong></p>
<ul>
  <li><code>types</code>: A list of <code>Breath</code> objects representing the breath exercise types.</li>
  <li><code>onClick</code>: A callback function that takes a <code>String</code> parameter representing the label of the clicked exercise tile.</li>
</ul>

<p><strong>Usage</strong></p>
<p>The <code>BreathExerciseList</code> composable is used internally within the <code>Dashboard</code> composable and doesn't need to be called directly.</p>

<p><strong>Functionality</strong></p>
<ol>
  <li>The <code>LazyVerticalGrid</code> composable is used to create a grid layout with a fixed number of columns.</li>
  <li>The list of exercise types (<code>types</code>) is iterated using the <code>items</code> composable function, which allows efficient composition of only the visible items.</li>
  <li>For each exercise type, a <code>BreathTypeTile</code> composable is rendered, passing the exercise's label, URL, and the <code>onClick</code> callback function.</li>
</ol>

<h3>BreathTypeTile Composable</h3>

<p><strong>Overview</strong></p>
<p>The <code>BreathTypeTile</code> Composable represents an individual breath exercise tile. It displays a card with an image and a label for each exercise type. It also receives a callback function to handle click events on the tile.</p>

<p><strong>Parameters</strong></p>
<ul>
  <li><code>label</code>: A <code>String</code> representing the label of the exercise type.</li>
  <li><code>url</code>: A <code>String</code> representing the URL of the image for the exercise type.</li>
  <li><code>onClick</code>: A callback function that takes a <code>String</code> parameter representing the label of the clicked exercise tile.</li>
</ul>

<p><strong>Usage</strong></p>
<p>The <code>BreathTypeTile</code> composable is used internally within the <code>BreathExerciseList</code> composable and doesn't need to be called directly.</p>

<p><strong>Functionality</strong></p>
<ol>
  <li>The <code>Card</code> composable is used to create a card-like container for the exercise tile.</li>
  <li>Clicking on the card triggers the provided <code>onClick</code> callback function, passing the label of the clicked exercise tile.</li>
  <li>The <code>Box</code> composable is used to create a container for the image and label.</li>
  <li>The <code>AsyncImage</code> composable is used to display the exercise image retrieved from the provided URL.</li>
  <li>The <code>Text</code> composable is used to display the label of the exercise type.</li>
</ol>


<h1>Timer</h1>
<h2>Codebase Documentation</h2>

<h3>ScheduleTimer Composable</h3>

<p><strong>Overview</strong></p>
<p>The <code>ScheduleTimer</code> Composable represents the screen for scheduling a breath exercise timer. It displays a countdown timer for the selected exercise type. It receives a <code>BreathViewModel</code> instance, the exercise type, and a callback function for handling the back button press.</p>

<p><strong>Parameters</strong></p>
<ul>
  <li><code>breathViewModel</code>: An instance of <code>BreathViewModel</code> that provides access to the breath exercise data and related operations.</li>
  <li><code>type</code>: A <code>String</code> representing the exercise type.</li>
  <li><code>onBackPressed</code>: A callback function to handle the back button press.</li>
</ul>

<p><strong>Usage</strong></p>
<p>To use the <code>ScheduleTimer</code> Composable, call it from your UI code and pass the required parameters. For example:</p>

<pre><code>ScheduleTimer(
    breathViewModel = breathViewModel,
    type = "ExerciseType",
    onBackPressed = {
        // Handle back button press
    }
)
</code></pre>

<p><strong>Functionality</strong></p>
<ol>
  <li>The Composable function collects the <code>breathExercise</code> state from the provided <code>BreathViewModel</code>.</li>
  <li>It launches an effect using <code>LaunchedEffect</code> to fetch the exercise based on the provided type using <code>getExerciseBasedOnType()</code> on the <code>breathViewModel</code>.</li>
  <li>The UI is built using the <code>Scaffold</code> composable from the Jetpack Compose Material library.</li>
  <li>The top app bar is created using the <code>TopAppBar</code> composable and displays the exercise type as the title. It also includes a back button to handle the <code>onBackPressed</code> callback.</li>
  <li>The content area of the screen contains the <code>Timer</code> composable.</li>
</ol>

<h3>Timer Composable</h3>

<p><strong>Overview</strong></p>
<p>The <code>Timer</code> Composable is responsible for rendering the countdown timer screen. It displays the background image and the countdown timer itself. It receives the <code>breath</code> object representing the exercise and a callback function to handle the back button press.</p>

<p><strong>Parameters</strong></p>
<ul>
  <li><code>modifier</code>: A <code>Modifier</code> specifying the layout attributes of the <code>Timer</code> composable.</li>
  <li><code>breath</code>: A <code>Breath</code> object representing the breath exercise.</li>
  <li><code>onBackPressed</code>: A callback function to handle the back button press.</li>
</ul>

<p><strong>Usage</strong></p>
<p>The <code>Timer</code> composable is used internally within the <code>ScheduleTimer</code> composable and doesn't need to be called directly.</p>

<p><strong>Functionality</strong></p>
<ol>
  <li>The <code>Box</code> composable is used as a container to hold the timer screen content.</li>
  <li>If the <code>breath</code> object is <code>null</code>, the <code>ErrorType</code> composable is displayed, indicating that an appropriate type of breathing exercise could not be found.</li>
  <li>If the <code>breath</code> object is not <code>null</code>, the <code>CountDownScheduler</code> composable is displayed, showing the countdown timer for the exercise.</li>
</ol>

<h3>CountDownScheduler Composable</h3>

<p><strong>Overview</strong></p>
<p>The <code>CountDownScheduler</code> Composable is responsible for rendering the countdown timer UI. It displays the background image, a circular dial representing the progress of the timer, and handles the timer expiration event. It receives the <code>breath</code> object representing the breath exercise and a callback function to handle the timer expiration.</p>

<p><strong>Parameters</strong></p>
<ul>
  <li><code>breath</code>: A <code>Breath</code> object representing the breath exercise.</li>
  <li><code>onBackPressed</code>: A callback function to handle the back button press.</li>
</ul>

<p><strong>Usage</strong></p>
<p>The <code>CountDownScheduler</code> composable is used internally within the <code>Timer</code> composable and doesn't need to be called directly.</p>

<p><strong>Functionality</strong></p>
<ol>
  <li>The <code>Box</code> composable is used as a container to hold the countdown timer content.</li>
  <li>The background image is displayed using the <code>AsyncImage</code> composable.</li>
  <li>The <code>CountDownTimer</code> composable is used to display the circular dial representing the progress of the timer. It receives the <code>actionList</code> representing the actions for the breath exercise, the background color, progress color, border color, and the <code>onTimerExpired</code> callback function to handle the expiration of the timer.</li>
</ol>

