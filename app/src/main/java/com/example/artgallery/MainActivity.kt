package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                Surface(
                    color = Color(0xFFFFF7AB),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ArtLayout()
                }
            }
        }
    }
}

// App Layout
@Composable
fun ArtLayout(modifier: Modifier = Modifier) {

    // Image, Title of Image and Description of said Image
    var artworkImage by remember { mutableIntStateOf(R.drawable.cat) }
    var titleOfImage by remember { mutableIntStateOf(R.string.cattitle) }
    var descOfImage by remember { mutableIntStateOf(R.string.catdesc) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ArtImage(
            artImage = artworkImage,
        )
        ArtistTitleandDesc(
            photoTitle = titleOfImage,
            photoDesc = descOfImage,
        )
        // Two buttons (prev & next) with switches for choosing images, titles and descriptions
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ButtonPreviousImage { when (artworkImage) {
                    R.drawable.cat -> {
                        artworkImage = R.drawable.asprospiti
                        titleOfImage = R.string.viewtitle
                        descOfImage = R.string.viewdesc
                    }
                    R.drawable.asprospiti -> {
                        artworkImage = R.drawable.setup
                        titleOfImage = R.string.setuptitle
                        descOfImage = R.string.setupdesc
                    }
                    R.drawable.setup -> {
                        artworkImage = R.drawable.dinosaur
                        titleOfImage = R.string.dinotitle
                        descOfImage = R.string.dinodesc
                    }
                    R.drawable.dinosaur -> {
                        artworkImage = R.drawable.cat
                        titleOfImage = R.string.cattitle
                        descOfImage = R.string.catdesc
                    }
                }
            }

            ButtonNextImage { when (artworkImage) {
                    R.drawable.cat -> {
                        artworkImage = R.drawable.dinosaur
                        titleOfImage = R.string.dinotitle
                        descOfImage = R.string.dinodesc
                    }
                    R.drawable.dinosaur -> {
                        artworkImage = R.drawable.setup
                        titleOfImage = R.string.setuptitle
                        descOfImage = R.string.setupdesc
                    }
                    R.drawable.setup -> {
                        artworkImage = R.drawable.asprospiti
                        titleOfImage = R.string.viewtitle
                        descOfImage = R.string.viewdesc
                    }
                    R.drawable.asprospiti -> {
                        artworkImage = R.drawable.cat
                        titleOfImage = R.string.cattitle
                        descOfImage = R.string.catdesc
                    }
                }
            }
        }
    }
}

// Abstract the Image and Hoist the State :)
@Composable
fun ArtImage(
    @DrawableRes artImage: Int,
) {
    val borderWidth = 6.dp
    Image(
        painter = painterResource(artImage),
        contentDescription = null,
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(borderWidth, Color(0xFF80ACAC))
            ),
    )
}

// This lives here to be used in the Layout. Hoisting is used.
@Composable
fun ArtistTitleandDesc(
    @StringRes photoTitle: Int,
    @StringRes photoDesc: Int,
) {
    Text(
        text = stringResource(photoTitle),
        fontFamily = FontFamily.Monospace,
        fontSize = 24.sp,
        modifier = Modifier.padding(10.dp),
    )
    Text(
        text = stringResource(photoDesc),
        fontFamily = FontFamily.Monospace,
        fontSize = 16.sp,
        modifier = Modifier.padding(10.dp),
    )
}

// Elevated button composables
@Composable
fun ButtonPreviousImage(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Previous")
    }
}

@Composable
fun ButtonNextImage(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }) {
        Text("Next")
    }
}