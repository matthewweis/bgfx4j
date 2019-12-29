import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.BiPredicate

val currentPath = Paths.get("").toAbsolutePath()

if (println(currentPath.fileName) != "shaders") {
    throw RuntimeException("must be in shaders path of bgfx4j")
}

val dirs = Files.find(currentPath, Integer.MAX_VALUE, BiPredicate { t, u -> u.isDirectory })

dirs.forEach {
    val files = Files.find(it, Integer.MAX_VALUE, BiPredicate { t, u -> u.isRegularFile })


    val valid = files.anyMatch { f -> f.startsWith("def_") } &&
            files.anyMatch { f -> f.startsWith("fs_") } &&
            files.anyMatch { f -> f.startsWith("vs_") }

    if (valid) {
        val def = files.filter { f -> f.startsWith("def_") }.findAny().get()
        val fs = files.filter { f -> f.startsWith("fs_") }.findAny().get()
        val vs = files.filter { f -> f.startsWith("vs_") }.findAny().get()


    }

}