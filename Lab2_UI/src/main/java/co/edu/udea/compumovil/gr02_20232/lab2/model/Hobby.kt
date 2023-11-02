package co.edu.udea.compumovil.gr02_20232.lab2.model

import kotlinx.serialization.Serializable

@Serializable
data class Hobby (
    var id: Int = 0,
    var hobbie: String = ""
)


@Serializable
data class HobbyRepository(
    val hobbies: List<Hobby>
)