package day07

class Node {
    val name: String
    val isDir: Boolean
    val children: MutableMap<String, Node> = mutableMapOf()
    var size: Int
        private set
    var parent: Node? = null
        private set

    constructor(name: String, size: Int, isDir: Boolean) {
        this.name = name
        this.size = size
        this.isDir = isDir
    }

    constructor(name: String, isDir: Boolean) : this(name, 0, isDir)

    fun addChild(child: Node) {
        children[child.name] = child
        child.parent = this
    }

    override fun toString(): String {
        return "Node(name='$name', size=$size, isDir=$isDir, children=${children.keys})"
    }

    fun calculateFileSizes(): Int {
        size = children.values.sumOf { child ->
            if (child.isDir) {
                child.calculateFileSizes()
            } else {
                child.size
            }
        }
        return size
    }
}