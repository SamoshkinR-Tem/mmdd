package com.artsam.presentation.compose.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.artsam.presentation.R
import com.artsam.presentation.compose.ui.toolbar.Toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class PicturesFragment : Fragment() {
    private val viewModel: PicturesViewModel by viewModel()
}

@Preview
@Composable
fun PicturesScreen() {
    Column() {
        Toolbar()
        ChipsRow()
        PicturesGrid()
    }
}

@Composable
fun ChipsRow(
    //cars: List<MukundaPicture> = getAllCars(),
    //selectedCar: Car? = null,
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
private fun PicturesGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(count = 15) {
            PicturesGridItem("\"Ghovardhan\"", "$100")
        }
    }
}

@Composable
private fun PicturesGridItem(pictureName: String, picturePrice: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = 1.dp
    ) {
        Column() {
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
