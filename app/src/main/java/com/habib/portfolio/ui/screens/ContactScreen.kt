package com.habib.portfolio.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.habib.portfolio.data.PortfolioData
import com.habib.portfolio.ui.components.*

@Composable
fun ContactScreen() {
    val ctx = LocalContext.current
    val clipboard = LocalClipboardManager.current
    var name by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Let's work together", style = MaterialTheme.typography.headlineMedium)
        Text("Tap to open, or copy with the button.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

        ContactCard(Icons.Rounded.Email, "Email", PortfolioData.EMAIL, { sendEmail(ctx, PortfolioData.EMAIL, "Hello from your portfolio", "") }) {
            clipboard.setText(AnnotatedString(PortfolioData.EMAIL)); Toast.makeText(ctx, "Email copied", Toast.LENGTH_SHORT).show()
        }
        ContactCard(Icons.Rounded.Phone, "Phone", "+92 371 0187540", { dial(ctx, PortfolioData.PHONE) }) {
            clipboard.setText(AnnotatedString(PortfolioData.PHONE)); Toast.makeText(ctx, "Number copied", Toast.LENGTH_SHORT).show()
        }
        ContactCard(Icons.Rounded.Link, "LinkedIn", "habib-rizvi", { openUrl(ctx, PortfolioData.LINKEDIN) }) {
            clipboard.setText(AnnotatedString(PortfolioData.LINKEDIN)); Toast.makeText(ctx, "Link copied", Toast.LENGTH_SHORT).show()
        }
        ContactCard(Icons.Rounded.Code, "GitHub", "hrizvi172", { openUrl(ctx, PortfolioData.GITHUB) }) {
            clipboard.setText(AnnotatedString(PortfolioData.GITHUB)); Toast.makeText(ctx, "Link copied", Toast.LENGTH_SHORT).show()
        }

        Spacer(Modifier.height(8.dp))
        Text("Send a message", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(name, { name = it }, Modifier.fillMaxWidth(), label = { Text("Your name") }, singleLine = true)
        OutlinedTextField(
            message, { message = it; showError = false }, Modifier.fillMaxWidth(), label = { Text("Message") },
            minLines = 4, isError = showError, supportingText = { if (showError) Text("Please write a message") }
        )
        Button(
            onClick = {
                if (message.isBlank()) showError = true
                else sendEmail(ctx, PortfolioData.EMAIL, "Portfolio message from ${name.ifBlank { "a visitor" }}", message)
            },
            Modifier.fillMaxWidth()
        ) {
            Icon(Icons.AutoMirrored.Rounded.Send, null); Spacer(Modifier.width(8.dp)); Text("Send via email")
        }
    }
}

@Composable
private fun ContactCard(icon: ImageVector, title: String, value: String, onClick: () -> Unit, onCopy: () -> Unit) {
    ElevatedCard(onClick = onClick, Modifier.fillMaxWidth()) {
        Row(Modifier.padding(horizontal = 16.dp, vertical = 10.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(value, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onCopy) { Icon(Icons.Rounded.ContentCopy, "Copy $title") }
        }
    }
}
