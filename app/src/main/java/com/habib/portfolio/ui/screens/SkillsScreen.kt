package com.habib.portfolio.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.habib.portfolio.data.PortfolioData
import com.habib.portfolio.data.Skill

@Composable
fun SkillsScreen() {
    var selected by rememberSaveable { mutableStateOf("All") }
    val filtered = remember(selected) {
        PortfolioData.skills.filter { selected == "All" || it.category == selected }
    }
    Column(Modifier.fillMaxSize()) {
        Text("Skills", Modifier.padding(start = 20.dp, top = 16.dp), style = MaterialTheme.typography.headlineMedium)
        LazyRow(contentPadding = PaddingValues(20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(PortfolioData.categories) { c ->
                FilterChip(selected == c, onClick = { selected = c }, label = { Text(c) })
            }
        }
        LazyColumn(contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(filtered, key = { it.name }) { SkillRow(it) }
        }
    }
}

@Composable
private fun SkillRow(skill: Skill) {
    var go by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { go = true }
    val progress by animateFloatAsState(if (go) skill.level else 0f, tween(900), label = "skill")
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(16.dp)) {
            Row {
                Text(skill.name, Modifier.weight(1f), style = MaterialTheme.typography.titleMedium)
                Text("${(progress * 100).toInt()}%", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(Modifier.height(10.dp))
            LinearProgressIndicator(progress = { progress }, Modifier.fillMaxWidth().height(8.dp), strokeCap = androidx.compose.ui.graphics.StrokeCap.Round)
        }
    }
}
