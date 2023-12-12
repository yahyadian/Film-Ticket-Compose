package com.yahya.filmticket.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yahya.filmticket.R

@Composable
fun ProfileScreen(
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.photo),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .clip(
                        CircleShape
                    )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Yahya Dian Prastyo", style = MaterialTheme.typography.titleMedium)
            Text(text = "yahyadianp@gmail.com", color = Color.Gray)
        }
    }
}