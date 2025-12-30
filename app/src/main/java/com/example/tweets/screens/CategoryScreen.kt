package com.example.tweets.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweets.R
import com.example.tweets.ui.theme.Typography
import com.example.tweets.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (categoryString: String) -> Unit) {

    val categoryVM: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryVM.categories.collectAsState()

    if (categories.value.isEmpty()) {

        //show loader
        Box(modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center){
            Text(text = "Loading", style = MaterialTheme.typography.bodyLarge)
        }
    } else {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, onClick)
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick: (categoryString: String) -> Unit) {

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick(category)
            }
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.wave_haikei),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, color = Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            category,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodyMedium


        )
    }
}