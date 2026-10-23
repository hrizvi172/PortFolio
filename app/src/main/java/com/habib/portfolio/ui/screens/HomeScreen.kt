package com.habib.portfolio.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.habib.portfolio.data.PortfolioData
import com.habib.portfolio.ui.components.*
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(onSkills: () -> Unit, onContact: () -> Unit) {
    val ctx = LocalContext.current
    val cs = MaterialTheme.colorScheme
    var roleIdx by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { while (true) { delay(2200); roleIdx = (roleIdx + 1) % PortfolioData.roles.size } }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 24.dp)) {
        item {
            Column(
                Modifier.fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(cs.primaryContainer, cs.background)))
                    .padding(top = 24.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Reveal(0) { Avatar(132.dp) }
                Spacer(Modifier.height(16.dp))
                Reveal(1) { Text(PortfolioData.NAME, style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center) }
                Reveal(2) {
                    AnimatedContent(
                        PortfolioData.roles[roleIdx], label = "role",
                        transitionSpec = { (slideInVertically { it } + fadeIn()) togetherWith (slideOutVertically { -it } + fadeOut()) }
                    ) { Text(it, style = MaterialTheme.typography.titleLarge, color = cs.primary) }
                }
                Spacer(Modifier.height(20.dp))
                Reveal(3) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = onSkills) { Text("My Skills") }
                        OutlinedButton(onClick = onContact) { Text("Contact Me") }
                    }
                }
            }
        }
        item {
            Reveal(4) {
                Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Stat("5.0★", "Upwork", Modifier.weight(1f))
                    Stat("3+", "Apps shipped", Modifier.weight(1f))
                    Stat("2", "Stacks", Modifier.weight(1f))
                }
            }
        }
        item {
            Reveal(5) {
                Column(Modifier.padding(20.dp)) {
                    Text("About me", style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.height(8.dp))
                    Text(PortfolioData.ABOUT, style = MaterialTheme.typography.bodyLarge, color = cs.onSurfaceVariant)
                }
            }
        }
        item { Text("Featured projects", Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.headlineMedium) }
        item { Spacer(Modifier.height(12.dp)) }
        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                items(PortfolioData.projects) { p ->
                    ElevatedCard(onClick = { openUrl(ctx, p.url) }, modifier = Modifier.width(280.dp)) {
                        Column(Modifier.padding(18.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(p.title, Modifier.weight(1f), style = MaterialTheme.typography.titleLarge)
                                Icon(Icons.AutoMirrored.Rounded.OpenInNew, "Open ${p.title}", tint = cs.primary)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(p.description, style = MaterialTheme.typography.bodyMedium, color = cs.onSurfaceVariant)
                            Spacer(Modifier.height(12.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                p.tech.forEach { SuggestionChip(onClick = {}, label = { Text(it, style = MaterialTheme.typography.labelSmall) }) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Stat(value: String, label: String, modifier: Modifier) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text(label, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
