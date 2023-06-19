package com.example.carpoint.utils

/**
 * A sealed class representing the different states of a resource.
 *
 * @param T The type of data associated with the resource.
 * @param data The data associated with the resource.
 * @param message The optional error message associated with the resource.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * Represents a successful state with the associated data.
     *
     * @param data The data associated with the resource.
     */
    class success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error state with the associated error message and optional data.
     *
     * @param message The error message associated with the resource.
     * @param data The optional data associated with the resource.
     */
    class error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Represents a loading state with the optional data.
     *
     * @param data The optional data associated with the resource.
     */
    class loading<T>(data: T? = null) : Resource<T>(data)
}
