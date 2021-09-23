package com.pasukanlangit.plugins

import com.pasukanlangit.model.User
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/") {
            val request = call.request
            //get url requested from client
            val url = request.uri
            //get header name list
            val headerNames = request.headers.names()
            //get specific header requested
            val userAgent = request.headers["User-Agent"]
            //get all query params name
            val queryParams = request.queryParameters.names()
            //get specific query params
            val testQueryParams = request.queryParameters["halahmboh"]

            println("Url requested: $url")
            println("All headers: $headerNames")
            println("headers user agent: $userAgent")
            println("All query params: $queryParams")
            println("All query params: $queryParams")
            print("Test query params: $testQueryParams")
            call.respondText("Hello World!")
        }

        get("/{id}"){
            val urlParams = call.parameters
            //get all url parameter send by client
            val parameterKeys = urlParams.names()
            //get specific url parameter send by client
            val parameterId = urlParams["id"]

            println("get all parameters name: $parameterKeys")
            println("value url params id: $parameterId")
        }

        post("/login"){
            //read json request body send by client
            val requestBody = call.receive<User>()

            println("json request body send by user: $requestBody")

            call.respondText("Endpoint working")
        }

        get("/users") {
            val request = call.request
            val isReturnSuccess = request.queryParameters["isSuccess"]

            val users = listOf(
                User("Ricko", "123456"),
                User("Farhan", "2455324"),
            )


            //to give additional message to client, optionally can put in header with -->
            //--> call.response.headers.append("key", "value")
            call.respond(status = if(!isReturnSuccess.isNullOrBlank() && isReturnSuccess == "false") HttpStatusCode.Forbidden else HttpStatusCode.OK, users)
        }

        get("/file"){
            val action = call.request.queryParameters["action"] ?: ""
            val file = File("files/profile.jpeg")

            //note that postman not understand http headers content dispotition, try in web browser
            call.response.header(
                HttpHeaders.ContentDisposition,
                if(action == "download") {
                    //attachment = download file
                    ContentDisposition.Attachment.withParameter(
                        ContentDisposition.Parameters.FileName, "myprofile.jpeg"
                    ).toString()
                }else{
                    //inline = open file
                    ContentDisposition.Inline.withParameter(
                        ContentDisposition.Parameters.FileName, "myprofile.jpeg"
                    ).toString()
                }
            )

            call.respondFile(file)
        }

        //if there are some endpoint but different http request method just use route to group
        route("/multiplemethod"){
            get {
                call.respondText("this from multiple method with get method")
            }

            post {
                call.respondText("this from multiple method with post method")
            }
        }

        get("/lorem"){
            call.respondText("lorem ipsum dolor sir amet")
        }

    }
}
