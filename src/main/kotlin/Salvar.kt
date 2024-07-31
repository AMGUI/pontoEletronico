import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import java.io.FileOutputStream

fun salvaPdf(data: List<List<String?>> ,local: String) {
    val document = Document(PageSize.A4)
    try {
        PdfWriter.getInstance(document, FileOutputStream(local))
        document.open()

        // Adiciona um título ao documento
        document.add(Paragraph("Dados dos Funcionários"))

        // Cria uma tabela com três colunas
        val table = PdfPTable(3)
        table.widthPercentage = 100f

        // Adiciona os cabeçalhos da tabela
        table.addCell(createCell("Data"))
        table.addCell(createCell("Hora"))
        table.addCell(createCell("Nome do Funcionário"))

        // Adiciona os dados à tabela
        for (row in data) {
            val dataHora = row[0]?.split(" ") ?: listOf("", "")
            val nome = row[1] ?: ""

            // Adiciona as células da tabela
            table.addCell(createCell(dataHora.getOrNull(0) ?: ""))
            table.addCell(createCell(dataHora.getOrNull(1) ?: ""))
            table.addCell(createCell(nome))
        }

        // Adiciona a tabela ao documento
        document.add(table)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        document.close()
    }
}

// Função auxiliar para criar células da tabela
fun createCell(content: String): PdfPCell {
    val cell = PdfPCell(Phrase(content))
    cell.horizontalAlignment = Element.ALIGN_CENTER
    cell.verticalAlignment = Element.ALIGN_MIDDLE
    return cell
}