package com.wil8dev.labrocante.view.prompt

import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun AddMedia(
    mediaUris: List<Uri>,
    maximumMediaCount: Int,
    onRemoveMedia: (Uri) -> Unit,
    onShowMediaOptions: () -> Unit,
) {
    val context = LocalContext.current
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        mediaUris
            .take(maximumMediaCount)
            .forEach { uri ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color(0xD3EEEEEE)),
                    contentAlignment = Alignment.Center
                ) {
                    val mimeType = context.contentResolver.getType(uri)

                    if (mimeType?.startsWith("image/") == true) {
                        val inputStream = context.contentResolver.openInputStream(uri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        inputStream?.close()

                        if (bitmap != null) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp),
                                contentScale = ContentScale.FillWidth,
                            )
                        } else {
                            Text("Unable to load image", color = Color.Red)
                        }
                    } else if (mimeType?.startsWith("video/") == true) {
                        AndroidView(
                            modifier = Modifier.size(100.dp),
                            factory = { context ->
                                VideoView(context).apply {
                                    setVideoURI(uri)
                                    start()
                                }
                            },
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove media",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                            .size(24.dp)
                            .clickable { onRemoveMedia(uri) }
                            .background(Color.White, shape = CircleShape),
                    )
                }
            }
        if (mediaUris.size < maximumMediaCount) {
            AddMediaButton(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xD3EEEEEE))
                    .clickable {
                        onShowMediaOptions()
                    },
            )
        }
    }
}

@Composable
private fun AddMediaButton(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = buildAnnotatedString {
                    append("Add media\n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W200)) {
                        append("(up to 6)")
                    }
                },
                textAlign = TextAlign.Center,
            )
        }
    }
}
