import kotlin.io.path.Path
import kotlin.io.path.readText

fun readTestInput(name: String): List<String> = Path("src/test/resources/$name.txt").readText().trim().lines()
