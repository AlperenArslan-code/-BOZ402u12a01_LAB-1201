package com.example.boz402u12p01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boz402u12p01.ui.theme.BOZ402u12p01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BOZ402u12p01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    // Örnek sanat eserleri listesi.
    val artworks = listOf(
        Artwork(R.drawable.ic_launcher_foreground, "Android Logo", "Google", "2008"),
        Artwork(R.drawable.ic_launcher_background, "Background Pattern", "Designer", "2023"),
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Sanat Eseri Görseli
        ArtworkImage(
            imageRes = currentArtwork.imageRes,
            contentDescription = currentArtwork.title
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        // Sanat Eseri Bilgileri
        ArtworkDescriptor(
            title = currentArtwork.title,
            artist = currentArtwork.artist,
            year = currentArtwork.year
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Kontrol Butonları
        DisplayController(
            onPreviousClick = {
                currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1
            },
            onNextClick = {
                currentIndex = (currentIndex + 1) % artworks.size
            }
        )
    }
}

@Composable
fun ArtworkImage(
    imageRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shadowElevation = 8.dp,
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(32.dp)
                .size(300.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ArtworkDescriptor(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
            Row {
                Text(
                    text = artist,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " ($year)",
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    BOZ402u12p01Theme {
        ArtSpaceApp()
    }
}
