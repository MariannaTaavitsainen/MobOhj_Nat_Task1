package com.example.heartratelimits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heartratelimits.ui.theme.HeartRateLimitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeartRateLimitsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HeartRateLimits(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HeartRateLimits(modifier: Modifier = Modifier){
    var ageInput by remember { mutableStateOf("")}
    val age = ageInput.toIntOrNull() ?: 0
    val upper = if (age > 0) (220 - age) * 0.85f else 0.0f
    val lower = if (age > 0) (220 - age) * 0.65f else 0.0f
    val upperFormatted = String.format("%.2f", upper)
    val lowerFormatted = String.format("%.2f", lower)

    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = ageInput,
            onValueChange = {ageInput = it},
            label = {Text (text = "Age")},
            singleLine = true,
            keyboardOptions =
                KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        ) // Textfield-elementti loppuu tähän

        Text(
            text = "Heart Rate Limits in your age are $lowerFormatted - $upperFormatted ",
            )
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HeartRateLimitsTheme {
        Greeting("Android")
    }
}