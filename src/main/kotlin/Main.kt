
val func= "C:\\Users\\Samsung\\Documents\\Dev\\womens-world-cup-2023-main (1)\\PontoEscolar\\src\\main\\resources\\FUNCIONARIOS.txt"
val ponto = "C:\\Users\\Samsung\\Documents\\Dev\\womens-world-cup-2023-main (1)\\PontoEscolar\\src\\main\\resources\\ponto.txt"
val novoponto = "C:\\Users\\Samsung\\Documents\\Dev\\womens-world-cup-2023-main (1)\\PontoEscolar\\src\\main\\resources\\relatorio.pdf"

fun main(){
    //startFuncionario()
  //  pontoStart()
    val x = obterNomeFunc(func)
    val y = obterDadosPonto(ponto)
    val z  = substituirCodigoPorNome(x ,y)
    salvaPdf( z,novoponto)

    println(z)
    }
