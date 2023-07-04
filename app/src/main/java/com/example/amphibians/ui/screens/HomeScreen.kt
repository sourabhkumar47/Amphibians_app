package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.Amphibian


@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibianUiState.Success -> AmphibiansListScreen(
            amphibianUiState.amphibians, modifier.fillMaxWidth()
        )

        is AmphibianUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
private fun AmphibiansListScreen(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = amphibians,
            key = { amphibian ->
                amphibian.name
            }
        ) { amphibian ->
            AmphibianDetailsCard(amphibian = amphibian, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun AmphibianDetailsCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.amphibian_title, amphibian.name, amphibian.type),
                modifier = modifier
                    .fillMaxWidth()
            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.img_src)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img)
            )

            Text(
                text = amphibian.description
            )

        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    CircularProgressIndicator()
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error), contentDescription = null
        )
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}