package com.yukaida.notecompose.activity.bloom

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yukaida.notecompose.R
import com.yukaida.notecompose.activity.ui.theme.caption
import com.yukaida.notecompose.activity.ui.theme.textButtonStyle
import com.yukaida.notecompose.ui.theme.gray
import com.yukaida.notecompose.ui.theme.pink100
import com.yukaida.notecompose.ui.theme.white

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold(bottomBar = { BottomBar() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(white)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar()
            BloomRowBanner()
            BloomInfoList()
        }
    }
}


data class ImageItem(val name: String, val resId: Int)

val bloomBannerList = listOf(
    ImageItem("Desert chic", R.drawable.desert_chic),
    ImageItem("Tiny terrariums", R.drawable.tiny_terrariums),
    ImageItem("Jungle Vibes", R.drawable.jungle_vibes)
)

val bloomInfoList = listOf(
    ImageItem("Monstera", R.drawable.monstera),
    ImageItem("Aglaonema", R.drawable.aglaonema),
    ImageItem("Peace lily", R.drawable.peace_lily),
    ImageItem("Fiddle leaf tree", R.drawable.fiddle_leaf),
    ImageItem("Desert chic", R.drawable.desert_chic),
    ImageItem("Tiny terrariums", R.drawable.tiny_terrariums),
    ImageItem("Jungle Vibes", R.drawable.jungle_vibes)
)

val navList = listOf(
    ImageItem("Home", R.drawable.ic_home),
    ImageItem("Favorites", R.drawable.ic_favorite_border),
    ImageItem("Profile", R.drawable.ic_account_circle),
    ImageItem("Cart", R.drawable.ic_shopping_cart)
)

@Composable
fun SearchBar() {
    Box() {
        TextField(
            value = "", onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(
                    RoundedCornerShape(4.dp)
                )
                .border(BorderStroke(1.dp, Color.Black)),
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_search)),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            placeholder = {
                Text(text = "Search", style = textButtonStyle, color = gray)
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = white,
                focusedBorderColor = white
            )
        )
    }
}

@Preview
@Composable
fun PlantCardPreview() {
    DesignCard(bloomInfoList[0])
}

@Composable
fun PlantCard(plant: ImageItem) {
    Card(
        modifier = Modifier
            .size(136.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Image(
            painter = painterResource(id = plant.resId),
            contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
        )
        Text(
            text = plant.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun BloomRowBanner() {
    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Browse themes",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(modifier = Modifier.height(136.dp)) {
            items(bloomBannerList.size) {
                if (it != 0) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                PlantCard(bloomBannerList[it])
            }
        }
    }
}

@Composable
fun DesignCard(plant: ImageItem) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = plant.resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = plant.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = gray,
                        modifier = Modifier
                            .paddingFromBaseline(top = 24.dp)
                    )
                    Text(
                        text = "This is a description",
                        style = MaterialTheme.typography.headlineSmall,
                        color = gray,
                        modifier = Modifier
                    )
                }
                Checkbox(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .size(24.dp),
                    checked = false,
                    onCheckedChange = {
//                        plant.enable = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    }

}

@Composable
fun BloomInfoList() {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Design your home garden",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp)
            )
            Icon(
                painterResource(id = R.drawable.ic_filter_list),
                "filter",
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 56.dp)
        ) {
            items(bloomInfoList.size) {
                if (it != 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                    DesignCard(plant = bloomInfoList[it])
                }
            }
        }
    }
}


@Composable
fun BottomBar() {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(pink100)
    ) {
        navList.forEach() {
            NavigationBarItem(
                selected = ("Home" == it.name),
                onClick = { },
                icon = {
                    Icon(
                        painter = painterResource(id = it.resId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = it.name, style = caption, color = gray) })
        }
    }
}