package com.jetbrains.kmpapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jetbrains.kmpapp.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun EmptyScreenContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(MR.strings.no_data_available))
    }
}
