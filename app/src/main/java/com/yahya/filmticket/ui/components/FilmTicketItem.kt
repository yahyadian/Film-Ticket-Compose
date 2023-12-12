package com.yahya.filmticket.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yahya.filmticket.R
import com.yahya.filmticket.ui.theme.FilmTicketTheme
import com.yahya.filmticket.ui.theme.Shapes

@Composable
fun FilmTicketItem(
    image: Int,
    title: String,
    requiredPrice: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = stringResource(R.string.required_price, requiredPrice),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal
            ),
            color = Color.Gray
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FilmTicketPreview() {
    FilmTicketTheme {
        FilmTicketItem(R.drawable.img_1, "Animal", 55000)
    }
}