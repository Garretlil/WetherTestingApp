package com.example.wethertestingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.wethertestingapp.UIMain.MainCardWether
import com.example.wethertestingapp.UIMain.WeatherForecastScreen
import com.example.wethertestingapp.ui.theme.WetherTestingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WetherTestingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: WhetherViewModel by viewModels()
                    val navController = rememberNavController()
                    NavGraph(viewModel, navController = navController)
                }
            }
        }
    }
}
