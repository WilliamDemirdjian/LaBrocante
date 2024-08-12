package com.wil8dev.labrocante.view.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.wil8dev.labrocante.R
import com.wil8dev.labrocante.view.core.compose.LaBrocanteNavigationBarItem
import com.wil8dev.labrocante.view.core.compose.LaBrocantePreview
import com.wil8dev.labrocante.view.core.compose.LaBrocanteScaffold
import com.wil8dev.labrocante.view.core.compose.animatedGradientBorder
import com.wil8dev.labrocante.view.core.compose.icons.rememberEco
import com.wil8dev.labrocante.view.core.compose.theme.LaBrocanteTheme
import com.wil8dev.labrocante.view.home.Item.Category
import com.wil8dev.labrocante.view.home.Item.Object

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onAccountClicked: () -> Unit = {},
    onHelpMeFindClick: () -> Unit = {},
    onHelpMeSellClick: () -> Unit = {},
) {
    LaBrocanteScaffold(
        modifier = modifier
            .fillMaxSize(),
        topBarText = "La Brocante",
        showCart = true,
        selectedNavigationBarItem = LaBrocanteNavigationBarItem.Home,
        onAccountClicked = onAccountClicked,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(32.dp))
            New(
                onHelpMeFindClick = onHelpMeFindClick,
                onHelpMeSellClick = onHelpMeSellClick,
            )
            Spacer(modifier = Modifier.height(32.dp))
            ItemSection(
                title = "Categories",
                items = categoryList,
                imagesAspectRatio = 3f / 4f,
            )
            Spacer(modifier = Modifier.height(32.dp))
            ItemSection(
                title = "Featured items",
                items = featuredItems,
                imagesAspectRatio = 16f / 9f,
            )
            Spacer(modifier = Modifier.height(32.dp))
            //            HelpMeFindSection(
            //                onHelpMeFindClick = onHelpMeFindClick,
            //            )
            //            Spacer(modifier = Modifier.height(16.dp))
            //            HelpMeSellSection(
            //                onHelpMeSellClick = onHelpMeSellClick,
            //            )
            //            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun New(
    modifier: Modifier = Modifier,
    onHelpMeFindClick: () -> Unit = {},
    onHelpMeSellClick: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        SectionTitle(
            modifier = Modifier
                .fillMaxWidth(),
            title = "New",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Max)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.width(0.dp))
            Announcement(
                modifier = Modifier
                    .width(screenWidth / 1.2f),
                title = "Search with the assistant",
                description = "Ask us to find the perfect item for you",
                ctaLabel = "Find now ✨",
                horizontalAlignment = Alignment.Start,
                onClick = onHelpMeFindClick,
            )
            Announcement(
                modifier = Modifier
                    .width(screenWidth / 1.2f),
                title = "Sell with the assistant",
                description = "No more hassle of selling",
                ctaLabel = "Sell now 🚀",
                horizontalAlignment = Alignment.Start,
                onClick = onHelpMeSellClick,
            )
            Spacer(modifier = Modifier.width(0.dp))
        }
    }
}

@Composable
fun Announcement(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    ctaLabel: String? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .animatedGradientBorder(
                shape = shape,
            )
            .clickable(onClick = onClick),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFEEEEEE))
                .padding(
                    vertical = 8.dp,
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = horizontalAlignment,
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.Gray,
            )
            if (description != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = description,
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                )
            }
            //            Spacer(
            //                modifier = Modifier
            //                    .height(8.dp)
            //                    .weight(1f),
            //            )
            //        if (ctaLabel != null) {
            //            AnimatedGradientBorderButton(
            //                modifier = Modifier
            //                    .padding(horizontal = 16.dp),
            //                text = ctaLabel,
            //                onClick = onClick,
            //            )
            //        }
            //        Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AnimatedGradientBorderButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    val colors = listOf(
        Color(0xFF5B8CFF),
        Color(0xFFC5DBFA),
        Color(0xFFFDADEE),
        Color(0xFFFDADEE),
        Color(0xFFC5DBFA),
        Color(0xFF5B8CFF),
    )
    val brush = Brush.sweepGradient(colors)
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val degreesAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "degreesAnimation",
    )
    val shape = ButtonDefaults.shape
    Text(
        modifier = modifier
            .background(Color.White)
            .clip(shape)
            .clickable(onClick = onClick)
            .padding(2.dp)
            .drawWithContent {
                rotate(degreesAnimation) {
                    drawCircle(
                        brush = brush,
                        radius = size.width,
                        blendMode = BlendMode.SrcIn,
                    )
                }
                drawContent()
            }
            .background(
                color = ButtonDefaults.buttonColors().containerColor,
                shape = shape,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        text = text,
        color = Color.White,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
) {
    val containerColor = Color(0xFFEEEEEE)
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        value = "",
        onValueChange = { /* TODO: Handle search input */ },
        placeholder = {
            Text(
                text = "Search for your happiness",
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
    )
}

@Composable
private fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier
            .padding(horizontal = 16.dp),
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        color = Color.Black,
    )
}

@Composable
private fun ItemSection(
    modifier: Modifier = Modifier,
    title: String,
    items: List<Item>,
    imagesAspectRatio: Float,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        SectionTitle(
            title = title,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(items) { index, item ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                ItemCard(
                    imageUrl = item.imageUrl,
                    imageAspectRatio = imagesAspectRatio,
                    name = item.title,
                    price = if (item is Object) item.price else null,
                    saveC02 = if (item is Object) item.savedC02 else null,
                )
            }
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageAspectRatio: Float,
    name: String,
    price: String? = null,
    saveC02: String? = null,
    onClick: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isPreview = LocalInspectionMode.current
    Column(
        modifier = modifier
            .width(screenWidth / 2.5f)
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onClick,
            ),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(imageAspectRatio)
                .clip(RoundedCornerShape(8.dp)),
            painter = if (isPreview) {
                rememberAsyncImagePainter(R.drawable.ic_home)
            } else {
                rememberAsyncImagePainter(model = imageUrl)
            },
            contentDescription = name,
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 4.dp,
                    top = 4.dp,
                ),
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (price != null) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 4.dp,
                        ),
                    text = price,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (saveC02 != null) {
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFF8BC34A))
                        .padding(horizontal = 4.dp)
                        .clickable {
                            // TODO explain "$savedCo2 of CO2 equivalent saved by buying this item compared to a new one"
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = saveC02,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape),
                        imageVector = rememberEco(),
                        contentDescription = "Add to cart",
                        tint = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}

@Composable
private fun HelpMeFindSection(
    modifier: Modifier = Modifier,
    onHelpMeFindClick: () -> Unit = {},
) {
    ArticleSection(
        modifier = modifier,
        title = "Help me choose",
        shortDescription = "Ask us to find the perfect item for you",
        detailedDescription = "Tell us by words or photos what you're looking for and what you're interested in.",
        ctaLabel = "Find now 🪄",
        onClick = onHelpMeFindClick,
    )
}

@Composable
private fun HelpMeSellSection(
    modifier: Modifier = Modifier,
    onHelpMeSellClick: () -> Unit = {},
) {
    ArticleSection(
        modifier = modifier,
        title = "Help me sell",
        shortDescription = "No more hassle of selling",
        detailedDescription = "Simply take a photo or video, and we'll do the rest!",
        ctaLabel = "Sell now 🚀",
        onClick = onHelpMeSellClick,
    )
}

@Composable
private fun ArticleSection(
    modifier: Modifier = Modifier,
    title: String,
    shortDescription: String,
    detailedDescription: String? = null,
    ctaLabel: String? = null,
    onClick: (() -> Unit?)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xD3EEEEEE))
            //            .onlyIfNotNull(onClick, ctaLabel) {
            //                clickable(onClick = { onClick?.invoke() })
            //            }
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle(
            modifier = Modifier
                .fillMaxWidth(),
            title = title,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = shortDescription,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = Color.Gray,
        )
        if (detailedDescription != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = detailedDescription,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = Color.Gray,
            )
        }
        if (onClick != null && ctaLabel != null) {
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedGradientBorderButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = ctaLabel,
                onClick = { onClick() },
            )
        }
    }
}

val categoryList = listOf(
    Category(
        title = "Furniture",
        imageUrl = "https://cdn.usegalileo.ai/stability/3af12708-71c1-4e7f-85e8-073ecff4e704.png",
    ),
    Category(
        title = "Clothing",
        imageUrl = "https://cdn.usegalileo.ai/stability/ab675b54-edfd-4c56-98a0-d0494a7e1b56.png",
    ),
    Category(
        title = "Toys",
        imageUrl = "https://cdn.usegalileo.ai/stability/70c63ad0-91d1-46fa-84c3-7c850e815f84.png",
    ),
    Category(
        title = "Electronics",
        imageUrl = "https://cdn.usegalileo.ai/stability/a37ab980-5176-4736-a972-c9450ff0fc26.png",
    ),
    Category(
        title = "Books",
        imageUrl = "https://cdn.usegalileo.ai/stability/c6739bc6-2ede-470a-af60-eb04e554fd7b.png",
    ),
)
val featuredItems = listOf(
    Object(
        imageUrl = "https://cdn.usegalileo.ai/stability/89157536-1af8-4256-94e8-0f49c27c0d46.png",
        title = "Mid-century chair",
        price = "$200",
        savedC02 = "100kg",
    ),
    Object(
        imageUrl = "https://cdn.usegalileo.ai/stability/a7d5430d-643c-4839-82d1-4f8eb60eee9a.png",
        title = "Vintage dress",
        price = "$50",
        savedC02 = "10kg",
    ),
    Object(
        imageUrl = "https://cdn.usegalileo.ai/sdxl10/e51aff48-8d14-4be4-a4e0-92b99d9cbd50.png",
        title = "Antique lamp",
        price = "$100",
        savedC02 = "15kg",
    ),
)

sealed class Item(
    open val title: String,
    open val imageUrl: String,
) {

    data class Category(
        override val title: String,
        override val imageUrl: String,
    ) : Item(title, imageUrl)

    data class Object(
        override val title: String,
        override val imageUrl: String,
        val price: String,
        val savedC02: String? = null,
    ) : Item(title, imageUrl)
}

@Composable
@LaBrocantePreview
private fun Preview() {
    LaBrocanteTheme {
        HomeContent()
    }
}
