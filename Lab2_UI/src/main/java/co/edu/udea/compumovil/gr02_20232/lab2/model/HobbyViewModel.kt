package co.edu.udea.compumovil.gr02_20232.lab2.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class HobbyViewModel: ViewModel() {

    val _repositories: MutableState<HobbyRepository> = mutableStateOf(
        HobbyRepository(
            hobbies = listOf()
        )
    )

    val repositories: MutableState<HobbyRepository> = _repositories

    fun getHobbies() {
        val url = "https://6541bf50f0b8287df1fec580.mockapi.io/api/lista/users"

        var header: HashMap<String, String>  = hashMapOf()
        Fuel.get(url).header(header).responseString { request, response, result ->
            Log.d("DEBUG", result.toString() + "soy yo")
            when(result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    if(ex.response.statusCode == 404) {
                        var tmp = HobbyRepository(hobbies = listOf())
                        _repositories.value = tmp
                    }
                }

                is Result.Success -> {

                    val jsonString = result.get()
                    val tmp = Json.decodeFromString<List<Hobby>>(jsonString)
                    val hobbyRepository = HobbyRepository(hobbies = tmp)
                    _repositories.value = hobbyRepository
                }

                else -> {
                    var tmp = HobbyRepository(hobbies = listOf())
                    _repositories.value = tmp
                }
            }
        }
    }
}