package com.example.afinal.presentation.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.example.afinal.presentation.DarkBlue
import com.example.afinal.presentation.LightText


data class CalculatorOperation(
    var firstOperand: Double = 0.0,
    var operator: String = "",
    var secondOperand: Double = 0.0
)

@Composable
fun CalculatorButtonRow(
    buttons: List<String>,
    onButtonClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        buttons.forEach { button ->
            CalculatorButton(
                text = button,
                onClick = { onButtonClick(button) },
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.size(40.dp)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkBlue,
            contentColor = LightText
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}