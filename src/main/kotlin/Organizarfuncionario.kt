
import java.io.File

fun startFuncionario() {
    val fileName = "C:\\Users\\Samsung\\Documents\\Dev\\womens-world-cup-2023-main (1)\\PontoEscolar\\src\\main\\resources\\FUNCIONARIOS.txt"
    processFileFuncionario(fileName)
}

  fun processFileFuncionario(fileName: String) {
    val file = File(fileName)
    if (!file.exists()) {
        println("O arquivo $fileName não existe.")
        return
    }

    val lines = file.readLines()
    val processedLines = lines.mapNotNull { processLineFuncionario(it) }

    file.printWriter().use { out ->
        processedLines.forEach { out.println(it) }
    }
}

 fun processLineFuncionario(line: String): String? {
    val parts = line.split("[")
    if (parts.size < 6) {
        println("Linha inválida: $line")
        return null
    }

    val codigo = parts[1]
    val nome = parts[2]
    val outros = parts.subList(3, parts.size).joinToString("[") // Unir o restante dos dados

    return """
        Código: $codigo
        Nome: $nome
        Outros: $outros
    """.trimIndent()
}
