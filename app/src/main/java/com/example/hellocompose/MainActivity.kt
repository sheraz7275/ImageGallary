package com.example.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.hellocompose.data.ImageItem
import com.example.hellocompose.ui.theme.HelloComposeTheme
import com.example.hellocompose.viewmodel.ImageListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageListScreen()
                }
            }
        }
    }
}

@Composable
fun ImageListScreen(viewModel: ImageListViewModel = viewModel()) {
    val images by viewModel.images.collectAsState()
    if (images.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(images) { imageItem ->
                ImageListItem(image = imageItem)
            }
        }
    }
}

@Composable
fun ImageListItem(image: ImageItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            AsyncImage(
                model = image.download_url,
                contentDescription = image.author,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
            Text(
                text = image.author,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun HelloComposeTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}
