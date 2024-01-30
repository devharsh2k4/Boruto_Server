package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
    install(StatusPages){
        status(HttpStatusCode.NotFound){
            applicationCall, _ ->
            applicationCall.respond(
                message = " Page not Found",
                status = HttpStatusCode.NotFound
            )
        }
        exception<AuthenticationException>{
            call: ApplicationCall, _ ->

            call.respond(
                message = " We caught an Exception!!",
                status = HttpStatusCode.OK
            )
        }
    }

}