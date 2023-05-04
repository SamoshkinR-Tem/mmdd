package com.artsam.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.artsam.data.entity.Painting
import com.artsam.presentation.R
import com.artsam.presentation.ui.home.HomeViewModel.HomeUiState.Error
import com.artsam.presentation.ui.home.HomeViewModel.HomeUiState.Success
import com.artsam.presentation.ui.toolbar.Toolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState() // val paintings = viewModel.getPaintings().collectAsState(listOf())

    Column() {
        Toolbar()
        ChipsRow()
        when (uiState) {
            is Error -> TODO()
            is Success -> PaintingsGrid((uiState as Success).paintings) { viewModel.addOneItem(it) }
        }
    }
}

@Composable
fun ChipsRow(
    onSelectedChanged: (String) -> Unit = {},
) {
    LazyRow(modifier = Modifier.padding(16.dp, 16.dp, 16.dp)) {
        items(count = 10) {
            Chip(
                name = "10 x 15",
                isSelected = false,
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
            )
        }
    }
}

@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun PaintingsGrid(
    paintings: List<Painting> = listOf(),
    onItemClick: (id: String) -> Unit = {},
    //selectedPainting: {Painting}? = null,
) {

    //val paintings = remember { mutableStateOf(1) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(count = paintings.size) {
            PaintingsGridItem(
                paintings[it].id, // "af7c1fe6-d669-414e-b066-e9733f0de7a8",
                paintings[it].paint, //"\"Ghovardhan\"",
                "$100"
            ) { id -> onItemClick(id) }
        }
    }
}

@Composable
private fun PaintingsGridItem(
    paintingId: String,
    pictureName: String,
    picturePrice: String,
    onItemClick: (id: String) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .clickable { onItemClick(paintingId) }
        ) {
            Image(
                painter = painterResource(R.drawable.ghovardhan),
                contentDescription = "Ghovardhan"
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = pictureName)
                Text(text = picturePrice)
            }
        }
    }
}
