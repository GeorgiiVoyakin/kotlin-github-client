package ru.mirea.ikbo1319

import com.google.gson.JsonParser
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import java.util.Scanner

suspend fun main() {
    println("--------------------------------")
    println("| Github API demonstration app |")
    println("--------------------------------")
    print("Enter GitHub username: ")

    val endpoint = "https://api.github.com"
    val httpClient = HttpClient(CIO)

    val scanner = Scanner(System.`in`)
    val username = scanner.next()

    println("To show user repositories -> enter 1")
    println("To show user followers -> enter 2")
    println("To show user organizations -> enter 3")

    when(scanner.nextInt()) {
        // print user repositories
        1 -> { printProperties(httpClient, "$endpoint/users/$username/repos", "name") }
        // print user followers
        2 -> { printProperties(httpClient, "$endpoint/users/$username/followers", "login") }
        // print user organizations
        3 -> { printProperties(httpClient, "$endpoint/users/$username/orgs", "login") }
        else -> throw Exception("Illegal operation")
    }
}

suspend fun printProperties(httpClient: HttpClient, endpoint: String, propertyName: String) {
    val response: HttpResponse = httpClient.request(endpoint)
    val rawData: String = response.receive()
    val json = JsonParser.parseString(rawData).asJsonArray
    json.forEach { elem -> println(elem.asJsonObject.get(propertyName)) }
}