package day07

import readInput

enum class FileType {
    DIRECTORY,
    FILE
}

interface FileSystemObject {
    
    fun getName(): String
    
    fun getSize(): Int
    
    fun getType(): FileType
    
    fun addChild(child: FileSystemObject)
    
    fun getChildren(): List<FileSystemObject>
    
    fun getParent(): FileSystemObject?
    
    fun plusSize(size: Int)
}

class Directory(
    private val name: String,
    private val parent: FileSystemObject?) : FileSystemObject {

    private val children = mutableListOf<FileSystemObject>()
    private var size = 0

    override fun getName(): String = name

    override fun getSize(): Int = size

    override fun getType(): FileType = FileType.DIRECTORY
    
    override fun addChild(child: FileSystemObject) {
        children.add(child)
    }

    override fun getChildren(): List<FileSystemObject> = children

    override fun getParent(): FileSystemObject? = parent
    override fun plusSize(size: Int) {
        this.size += size 
    }
}

class File (
    private val name: String,
    private val size: Int,
    private val parent: FileSystemObject?)
: FileSystemObject {

    override fun getName(): String = name

    override fun getSize(): Int = size

    override fun getType(): FileType = FileType.FILE
    
    override fun addChild(child: FileSystemObject) {
    
    }

    override fun getChildren(): List<FileSystemObject> {
        return emptyList()
    }

    override fun getParent(): FileSystemObject? = parent
    
    override fun plusSize(size: Int) {
        
    }
}

fun getDirectories(input: List<String>): List<FileSystemObject> {
    val root = Directory("/", null)
    var workingDirectory: FileSystemObject? = null

    val allDirectories = mutableListOf<FileSystemObject>()
    allDirectories.add(root)
    input.forEach { line ->
        val parts = line.split(" ")
        when (parts[0] ) {
            "$" -> {
                if (parts[1] == "cd") {
                    workingDirectory = if (parts[2] == "..") {
                        workingDirectory!!.getParent()
                    } else if (parts[2] == "/") {
                        root
                    } else {
                        var cd: FileSystemObject? = null
                        for (child in workingDirectory!!.getChildren())
                            if (child.getType() == FileType.DIRECTORY && child.getName() == parts[2]) {
                                cd = child
                                break
                            }
                        cd
                    }
                }
                if (parts[1] == "ls") {
                    // nothing to do
                }
            }
            "dir" -> {
                val tmpDirectory = Directory(parts[1], workingDirectory)
                workingDirectory!!.addChild(tmpDirectory)
                allDirectories.add(tmpDirectory)
            }
            else -> {
                val file = File(parts[1], parts[0].toInt(), workingDirectory)
                workingDirectory!!.addChild(file)
                workingDirectory!!.plusSize(file.getSize())

                var tmp = workingDirectory!!.getParent()
                while (tmp != null) {
                    tmp.plusSize(file.getSize())
                    tmp = tmp.getParent()
                }
            }
        }
    }

    return allDirectories
}

fun main() {

    fun part1(input: List<String>): Int {
        var size = 0
        for (dir in getDirectories(input)) {
            if (dir.getSize() < 100000)
                size += dir.getSize()
        }
        return size
    }

    fun part2(input: List<String>): Int {
        val directories = getDirectories(input)
        val root = directories[0]

        val DISK_SPACE_AVAILABLE = 70000000
        val NEED_UNUSED = 30000000
        
        val spaceLeft = DISK_SPACE_AVAILABLE - root.getSize()
        val spaceToRemove = NEED_UNUSED - spaceLeft
        
        val candidates = mutableSetOf<Int>()
        for (dir in directories)
            if (dir.getSize() > spaceToRemove)
                candidates.add(dir.getSize())
        
        return candidates.elementAt(candidates.size -1)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day07/Day07_test")
    check(part1(testInput) == 95437)


    val input = readInput("day07/Day07")
    println(part1(input))
    check(part2(testInput) == 24933642)
    println(part2(input))
}
