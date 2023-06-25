/**
Car.kt
Description: Car object.
Author: Mohammed Alamer
Last Change: 25.06.2023
 */
package com.example.carpoint.models

import java.util.UUID

/**
 * Data class representing a car.
 *
 * @property id The unique identifier of the car.
 * @property brand The brand of the car.
 * @property model The model of the car.
 * @property year The manufacturing year of the car.
 * @property user The user associated with the car.
 */
data class Car(
    val id: String = UUID.randomUUID().toString(),
    val brand: String,
    val model: String,
    val year: Int,
    val user: User
)
