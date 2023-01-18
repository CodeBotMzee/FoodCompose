package com.example.foodcompose.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.foodcompose.R
import com.example.foodcompose.ui.theme.Primary
import com.example.foodcompose.ui.theme.White


@Preview
@Composable
fun FoodItemCard() {
    Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.food_item_1),
            contentDescription = "food Item",
            modifier = Modifier
                .size(235.dp)
                .align(Alignment.TopCenter)
                .zIndex(2f)
        )
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .width(220.dp)
                .padding(top = 50.dp),
            shape = RoundedCornerShape(35.dp),
            backgroundColor = White,
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 50.dp,
                    end = 50.dp,
                    bottom = 40.dp,
                    top = 135.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Veggie Tomato Mix",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Rs 1900",
                    modifier = Modifier.padding(top = 15.dp),
                    color = Primary,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
