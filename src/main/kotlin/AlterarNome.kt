import java.io.File

fun obterNomeFunc(fileName: String): List<List<String>> {
    val funcionarios = mutableListOf<List<String>>()
    val file = File(fileName)

    var codigo = ""
    var nome = ""

    file.forEachLine { line ->
        when {
            line.startsWith("Código:") -> {
                codigo = line.split(":")[1].trim()
            }
            line.startsWith("Nome:") -> {
                nome = line.split(":")[1].trim()
                funcionarios.add(listOf(codigo, nome))
            }
        }
    }

    return funcionarios
}

fun obterDadosPonto(fileName: String): List<List<String>> {
    val dadosPonto = mutableListOf<List<String>>()
    val file = File(fileName)

    if (!file.exists()) {
        throw IllegalArgumentException("Arquivo não encontrado: $fileName")
    }

    var dataHora = ""
    var codigoColaborador = ""

    file.forEachLine { line ->
        when {
            line.startsWith("Data e Hora:") -> {
                dataHora = line.split(":")[1].trim() + ":"+ line.split(":")[2].trim()
            }
            line.startsWith("Código do Colaborador:") -> {
                codigoColaborador = line.split(":")[1].trim().take(11)  // Limita o CPF a 11 caracteres
                if (dataHora.isNotEmpty() && codigoColaborador.isNotEmpty()) {
                    dadosPonto.add(listOf(dataHora, codigoColaborador))
                    dataHora = "" // Reset para evitar reutilização acidental
                }
            }
        }
    }

    return dadosPonto
}

fun substituirCodigoPorNome(
    funcionarios: List<List<String>>,
    pontos: List<List<String>>
): List<List<String?>> {
    val mapaFuncionarios = funcionarios.associate { it[0] to it[1] }
    val resultado = mutableListOf<List<String?>>()


    pontos.forEach { ponto ->
        val codigoColaborador = ponto[1]
        val data = ponto[0]
        funcionarios.forEach {
            val codigox = it[0]
            if (codigoColaborador == codigox.dropLast(1) ) {
                val lista: List<String?> = listOf (data ,it[1])
                resultado.add(lista)
            }
        }
    }

    return resultado

}