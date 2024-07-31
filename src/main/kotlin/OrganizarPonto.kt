
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

fun pontoStart() {
    val fileName = "C:\\Users\\Samsung\\Documents\\Dev\\womens-world-cup-2023-main (1)\\PontoEscolar\\src\\main\\resources\\ponto.txt"
    processFile(fileName)

}

fun processFile(fileName: String) {
    val file = File(fileName)
    if (!file.exists()) {
        println("O arquivo $fileName não existe.")
        return
    }

    val lines = file.readLines()
    val processedLines = lines.mapNotNull { processLine(it) }

    file.printWriter().use { out ->
        processedLines.forEach { out.println(it) }
    }
}

fun processLine(line: String): String? {
    if (line.length != 38) {
        println("Linha inválida: $line")
        return null
    }

    val codigoInicio = line.substring(0, 10)
    val dataHoraRaw = line.substring(10, 22)
    val dataHora = formatDateTime(dataHoraRaw)
    val codigoColaborador = line.substring(22, 33)
    val codigoFinal = line.substring(33)

    return """
        Código: $codigoInicio
        Data e Hora: $dataHora
        Código do Colaborador: $codigoColaborador
        Código Final: $codigoFinal
    """.trimIndent()
}

fun formatDateTime(dataHoraRaw: String): String {
    val dateFormat = SimpleDateFormat("ddMMyyyyHHmm", Locale.getDefault())
    val date = dateFormat.parse(dataHoraRaw)
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return outputFormat.format(date)
}
