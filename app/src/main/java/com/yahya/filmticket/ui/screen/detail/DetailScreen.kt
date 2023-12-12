package com.yahya.filmticket.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yahya.filmticket.R
import com.yahya.filmticket.di.Injection
import com.yahya.filmticket.ui.ViewModelFactory
import com.yahya.filmticket.ui.common.UiState
import com.yahya.filmticket.ui.components.OrderButton
import com.yahya.filmticket.ui.components.ProductCounter
import com.yahya.filmticket.ui.theme.FilmTicketTheme

@Composable
fun DetailScreen(
    filmTicketId: Long,
    viewModel: DetailFilmTicketViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFilmTicketById(filmTicketId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.filmTicket.image,
                    data.filmTicket.title,
                    data.filmTicket.synopsis,
                    data.filmTicket.requiredPrice,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.filmTicket, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    synopsis: String,
    basePoint: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPrice by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(600.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(24.dp)
                        .clip(RoundedCornerShape(100))
                        .background(White.copy(alpha = 0.5f))
                        .padding(12.dp)
                        .clickable { onBackClick() },
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = stringResource(R.string.required_price, basePoint),
                    color = Color.Gray
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                )
                Text(
                    text = synopsis,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(LightGray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductCounter(
                1,
                orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )
            totalPrice = basePoint * orderCount
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPrice),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    FilmTicketTheme {
        DetailContent(
            R.drawable.img_1, "Animal", "Arjun Singh (Ranbir Kapoor) mengalami transformasi yang luar biasa ketika ikatan dengan ayahnya, Balbir Singh (Anil Kapoor) mulai renggang, dan keinginannya untuk balas dendam semakin kuat.", 55000,0,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}