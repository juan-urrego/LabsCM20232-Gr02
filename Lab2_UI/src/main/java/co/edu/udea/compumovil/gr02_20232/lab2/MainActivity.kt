package co.edu.udea.compumovil.gr02_20232.lab2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.theme.JetsurveyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetsurveyTheme {
                JetsurveyNavHost()
            }
        }
    }
}