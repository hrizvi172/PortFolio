package com.habib.portfolio.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

fun openUrl(ctx: Context, url: String) =
    runCatching { ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }

fun sendEmail(ctx: Context, to: String, subject: String, body: String) = runCatching {
    ctx.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    })
}

fun dial(ctx: Context, number: String) =
    runCatching { ctx.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))) }

/** Staggered fade + slide-in entrance. */
@Composable
fun Reveal(index: Int, content: @Composable () -> Unit) {
    var show by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(index * 90L); show = true }
    AnimatedVisibility(
        visible = show,
        enter = fadeIn(tween(500)) + slideInVertically(tween(500)) { it / 6 }
    ) { content() }
}

/** Shows res/drawable/profile.(jpg|png) if present, otherwise gradient initials. Compiles either way. */
@Composable
fun Avatar(size: Dp) {
    val ctx = LocalContext.current
    val id = remember { ctx.resources.getIdentifier("profile", "drawable", ctx.packageName) }
    Box(
        Modifier.size(size).clip(CircleShape)
            .background(Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary)))
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (id != 0) Image(painterResource(id), "Profile photo", Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        else Text("SH", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onPrimary)
    }
}
