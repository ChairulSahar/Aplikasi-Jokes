package com.d121211011.jokes.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211011.jokes.data.models.Jokes
import com.d121211011.jokes.ui.activities.detail.DetailActivity
import com.d121211011.jokes.ui.theme.JokesAppComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Scaffold(
                       topBar = {
                           TopAppBar(
                               title = { Text(text = "List Jokes") },
                           )
                       },
                       floatingActionButton = {
                           FloatingActionButton(onClick = {}) {
                               Icon(Icons.Default.Add, contentDescription = "Add")
                           }
                       }
                   ) {
                       Column(modifier = Modifier.padding(it)) {
                           val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                           ListJokesScreen(mainViewModel.mainUiState)
                       }
                   }
                }
            }
        }
    }

    @Composable
    private fun ListJokesScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when(mainUiState) {
            is MainUiState.Success -> ListJokes(mainUiState.jokes)
            is MainUiState.Error -> ErrorText()
            is MainUiState.Loading -> LoadingBar()
        }
    }

    @Composable
    private fun ErrorText() {
        Text(text = "ERROR")
    }

    @Composable
    fun LoadingBar() {
        Text(text = "SEDANG LOADING")
    }

    @Composable
    private fun ListJokes(jokes: List<Jokes>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(jokes) { jokes ->
                JokesCard(jokes = jokes)
            }
        }
    }

    @Composable
    private fun JokesCard(jokes: Jokes, modifier: Modifier = Modifier) {
        Card(modifier = Modifier.clickable {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JOKES", jokes)
            startActivity(intent)
        }) {
            Column {
                Text(text = jokes.setup ?: "Ini setup")
            }
        }
    }

}
