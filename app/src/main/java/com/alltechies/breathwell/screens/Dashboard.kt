package com.alltechies.breathwell.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.alltechies.breathwell.data.Breath
import com.alltechies.breathwell.viewmodel.BreathViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(
    breathViewModel: BreathViewModel,
    onClick: (String) -> Unit) {

    //Observe the list which will auto update once value is fetched inside ViewModel
    val types by breathViewModel.exerciseTypes.collectAsStateWithLifecycle()

    //Fetch the list of exercises
    LaunchedEffect(key1 = Unit) {
        breathViewModel.getListOfAllExercises()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "BreathWell", fontWeight = FontWeight.ExtraBold)

                        Text(text = "Person Name",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                BreathExerciseList(types, onClick)
            }
        },
        bottomBar = {
            Text(text = "Credits", modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp))
        }
    )
}

@Composable
fun BreathExerciseList(types: List<Breath>, onClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(types.size) { index ->
            val breath = types[index]
            BreathTypeTile(breath.type, breath.url, onClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathTypeTile(label: String, url: String, onClick: (String) -> Unit) {
    Card(
        onClick = { onClick(label) },
        modifier = Modifier.height(260.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Text(
                text = label,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(0.3f))
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}