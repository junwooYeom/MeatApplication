package com.junwooyeom.meatapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MeatScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable (() -> Unit) = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.surface,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}
