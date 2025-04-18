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

    fun part2(lines: List<String>): Int {
        val root = buildFileSystem(lines)
        val unusedSpace = 70_000_000 - root.size

        return findFiles(root, Node::isDir)
            .sortedBy { it.size }
            .find { it.size + unusedSpace >= 30_000_000 }?.size ?: -1
    }

    private fun findFiles(root: Node, predicate: (Node) -> Boolean): List<Node> {
        val matchingNodes =  if (predicate(root)) listOf(root) else emptyList()
        return matchingNodes + root.children.values.flatMap { findFiles(it, predicate) }
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
    printResult("2", Day07.part2(input))
}