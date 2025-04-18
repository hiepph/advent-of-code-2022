package day07

import printResult
import readLines

object Day07 {
    fun part1(lines: List<String>): Int {
        val root = buildFileSystem(lines)

        val predicate: (Node) -> Boolean = { node: Node -> node.isDir && node.size <= 100_000 }
        return findFiles(root, predicate)
            .sumOf { it.size }
    }

    private fun findFiles(root: Node, predicate: (Node) -> Boolean): List<Node> {
        val result = mutableListOf<Node>()
        if (predicate(root)) result.add(root)
        root.children.values.forEach { result.addAll(findFiles(it, predicate)) }
        return result
    }

    private fun buildFileSystem(lines: List<String>): Node {
        val root = Node("/", true)
        var current: Node = root

        lines.forEach { line ->
            when {
                line.startsWith("$ cd") -> {
                    val dirName = line.split(" ")[2]
                    when (dirName) {
                        "/" -> current = root
                        ".." -> current = current.parent!!
                        else -> current = current.children[dirName]!!
                    }
                }
                line.startsWith("$ ls") -> {
                    // Do nothing
                }
                line.startsWith("dir") -> {
                    val dirName = line.split(" ")[1]
                    val newDir = Node(dirName, true)
                    current.addChild(newDir)
                }
                else -> {
                    val (size, name) = line.split(" ")
                    val fileSize = size.toInt()
                    val newFile = Node(name, fileSize, false)
                    current.addChild(newFile)
                }
            }
        }

        root.calculateFileSizes()

        return root
    }
}

fun main() {
    val input = readLines("Day07")
    printResult("1", Day07.part1(input))
}