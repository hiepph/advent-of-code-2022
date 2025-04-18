import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, env: String = "main"): String =
    Path("src/$env/resources/$name.txt").readText().trim()

fun readLines(name: String, env: String = "main"): List<String> =
    readInput(name, env).lines()

/**
 * Printing results of part1 and part 2.
 */
fun printResult(part: String, result: Any?) {
    println("Part $part: $result")
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Product of all elements
 */
fun List<Int>.product() = fold(1) { acc, i -> acc * i }