package com.example.versioncatalogsample.remote.utils


sealed interface Exceptions {

    val message: String

    data class IOException(override val message: String = "IO Exception", val cause: Throwable) :
        Exceptions


    data class NetworkConnectionException(override val message: String = "Network Connection Error") :
        Exceptions


    data class RemoteDataSourceException(
        val code: String?,
        override val message: String = "Remote DataSource Error"
    ) : Exceptions
}