package com.example.a25_simple_calculator_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.a25_simple_calculator_kotlin.ui.theme._25_Simple_Calculator_KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _25_Simple_Calculator_KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorUI(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// UI structure
@Composable
fun CalculatorUI(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }

    Column {
        Text(text = displayText, modifier.fillMaxWidth(), textAlign = TextAlign.End, fontSize = 48.sp)
        Row {
            Button(onClick = { displayText += "1" }) { Text(text = "1")}
            Button(onClick = { displayText += "2" }) { Text(text = "2")}
            Button(onClick = { displayText += "3" }) { Text(text = "3")}
            Button(onClick = { displayText += "/" }) { Text(text = "/")}
        }
        Row {
            Button(onClick = { displayText += "4" }) { Text(text = "4")}
            Button(onClick = { displayText += "5" }) { Text(text = "5")}
            Button(onClick = { displayText += "6" }) { Text(text = "6")}
            Button(onClick = { displayText += "*" }) { Text(text = "*")}
        }
        Row {
            Button(onClick = { displayText += "7" }) { Text(text = "7")}
            Button(onClick = { displayText += "8" }) { Text(text = "8")}
            Button(onClick = { displayText += "9" }) { Text(text = "9")}
            Button(onClick = { displayText += "-" }) { Text(text = "-")}
        }
        Row {
            Button(onClick = { displayText = "0" }) { Text(text = "c")}
            Button(onClick = { displayText += "0" }) { Text(text = "0")}
            Button(onClick = { displayText = calculateResult(displayText) }) { Text(text = "=")}
            Button(onClick = { displayText += "+" }) { Text(text = "+")}
        }
    }

}

fun calculateResult(displayText: String): String{
    // split the text into two numbers and a operator
    val regex = Regex("([+\\-*/])")
    val parts = displayText.split(regex).filter { it.isNotEmpty() }

    if (parts.size <2) return displayText //in case only one number was entered

    val firstNumber = parts[0].toDoubleOrNull() ?: return "Error" //getting the first number
    val secondNumber = parts[1].toDoubleOrNull() ?: return "Error" //getting the second number

    // get the operator
    val operator = displayText.firstOrNull { it in "/*-+" } ?: return "Error"
    // do the calculation according to the operator
    return when (operator){
        '+' -> (firstNumber + secondNumber).toString()
        '-' -> (firstNumber - secondNumber).toString()
        '*' -> (firstNumber * secondNumber).toString()
        '/' -> if(secondNumber != 0.0) (firstNumber / secondNumber).toString() else "Error"
        else -> "Error"
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    _25_Simple_Calculator_KotlinTheme {
        CalculatorUI()
    }
}