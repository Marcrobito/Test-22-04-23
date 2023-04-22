package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.test.domain.Response
import com.example.test.ui.theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                val mainViewModel by viewModels<MainViewModel>()
                val state = mainViewModel.state.collectAsState()
                var search by remember { mutableStateOf("") }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    with(state.value) {
                        Column() {
                            Row() {
                                TextField(
                                    modifier = Modifier
                                        .weight(1f),
                                    value = search,
                                    onValueChange = {
                                        search = it.trim()
                                    },
                                    placeholder = {
                                        Text(text = "Search")
                                    }
                                )
                                Button(onClick = {
                                    mainViewModel.search(search)
                                    search = ""
                                }) {
                                    Text(text = "Search")
                                }
                            }

                            if (queries.isNotEmpty()){
                                LazyRow(contentPadding = PaddingValues(
                                    vertical = 4.dp,
                                    horizontal = 8.dp
                                )){
                                    items(queries){ query ->
                                        query.name?.let { name ->
                                            Button(onClick = {
                                                mainViewModel.search(name)
                                                search = ""
                                            }) {
                                                Text(text = name)
                                            }
                                        }
                                    }
                                }
                            }


                            LazyColumn(
                                contentPadding = PaddingValues(
                                    vertical = 4.dp,
                                    horizontal = 8.dp
                                )
                            ) {
                                items(data) { item ->
                                    val lastIndex = state.value.data.lastIndex
                                    val currentIndex = state.value.data.indexOf(item)
                                    Card(
                                        modifier = Modifier
                                            .height(120.dp)
                                            .fillMaxWidth()
                                            .padding(bottom = 16.dp),
                                        shape = RoundedCornerShape(12.dp),
                                    ) {
                                        Row() {
                                            GlideImage(
                                                model = item.image,
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .aspectRatio(1F),
                                                contentScale = ContentScale.Crop
                                            )
                                            Column(modifier = Modifier.padding(8.dp)) {
                                                Text(text = item.name)
                                                Text(text = item.price.toString())
                                            }
                                        }
                                    }
                                    if (lastIndex == currentIndex) {
                                        mainViewModel.getNext()
                                    }
                                }

                            }
                        }

                        error?.let {
                            //some error handler
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = it)
                            }
                        }

                        if (isLoading) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(Color.Black.copy(alpha = 0.6F))
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }

            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestTheme {
        Greeting("Android")
    }
}