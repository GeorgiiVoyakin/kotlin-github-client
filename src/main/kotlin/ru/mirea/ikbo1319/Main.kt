package ru.mirea.ikbo1319

import org.kohsuke.github.GHUser
import org.kohsuke.github.GitHub
import java.util.*

fun main() {
    val github: GitHub = GitHub.connect()

    println("--------------------------------")
    println("| Github API demonstration app |")
    println("--------------------------------")
    println("Search by GitHub username -> enter 1")

    val scanner = Scanner(System.`in`)

    when(scanner.nextInt()) {
        1 -> {
            print("Enter GitHub username: ")
            val user: GHUser = github.getUser(scanner.next())

            println("To show user repositories -> enter 1")
            println("To show user followers -> enter 2")
            println("To show user organizations -> enter 3")

            when(scanner.nextInt()) {
                1 -> user.repositories.forEach { r -> println(r.key) }
                2 -> user.followers.forEach { f -> println(f.login) }
                3 -> user.organizations.forEach{ o -> println(o.name)}
                else -> throw Exception("Illegal operation")
            }
        }
        else -> throw Exception("Illegal operation")
    }
}