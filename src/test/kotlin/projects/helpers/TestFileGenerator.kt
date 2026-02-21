package projects.helpers

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

object TestFileGenerator {

    fun createCsvFromContent(fileName: String, content: String): File {
        val file = File.createTempFile(fileName.substringBeforeLast("."), ".csv")
        file.writeText(content.trimIndent())
        return file
    }

    fun createXlsxFromContent(fileName: String, csvContent: String): File {
        return createExcelFromContent(fileName, csvContent, isXls = false)
    }

    fun createXlsFromContent(fileName: String, csvContent: String): File {
        return createExcelFromContent(fileName, csvContent, isXls = true)
    }

    private fun createExcelFromContent(fileName: String, csvContent: String, isXls: Boolean): File {
        val workbook: Workbook = if (isXls) HSSFWorkbook() else XSSFWorkbook()
        val sheet = workbook.createSheet("Projects")

        val lines = csvContent.trimIndent().lines().filter { it.isNotBlank() }

        lines.forEachIndexed { rowIndex, line ->
            val row = sheet.createRow(rowIndex)
            val values = parseCsvLine(line)

            values.forEachIndexed { colIndex, value ->
                val cell = row.createCell(colIndex)

                // Tenta converter para número se possível
                value.toDoubleOrNull()?.let {
                    cell.setCellValue(it)
                } ?: cell.setCellValue(value)
            }
        }

        val extension = if (isXls) ".xls" else ".xlsx"
        val file = File.createTempFile(fileName.substringBeforeLast("."), extension)

        FileOutputStream(file).use { outputStream ->
            workbook.write(outputStream)
        }
        workbook.close()

        return file
    }

    private fun parseCsvLine(line: String): List<String> {
        val values = mutableListOf<String>()
        val currentValue = StringBuilder()
        var inQuotes = false

        for (char in line) {
            when {
                char == '"' -> inQuotes = !inQuotes
                char == ',' && !inQuotes -> {
                    values.add(currentValue.toString().trim())
                    currentValue.clear()
                }
                else -> currentValue.append(char)
            }
        }
        values.add(currentValue.toString().trim())

        return values
    }

    fun createTestXlsxFile(fileName: String = "projects-batch.xlsx"): File {
        val workbook: Workbook = when {
            fileName.endsWith(".xls") -> HSSFWorkbook()
            else -> XSSFWorkbook()
        }

        val sheet = workbook.createSheet("Projects")

        // Header
        val headerRow = sheet.createRow(0)
        val headers = listOf(
            "clienteId",
            "concessionaria",
            "protocoloConcessionaria",
            "classe",
            "integrator",
            "modalidade",
            "enquadramento",
            "protecaoCC",
            "potenciaSistema"
        )
        headers.forEachIndexed { index, header ->
            headerRow.createCell(index).setCellValue(header)
        }

        // Data row 1
        val row1 = sheet.createRow(1)
        row1.createCell(0).setCellValue("550e8400-e29b-41d4-a716-446655440001")
        row1.createCell(1).setCellValue("CEMIG")
        row1.createCell(2).setCellValue("PROT-2024-001")
        row1.createCell(3).setCellValue("Residencial")
        row1.createCell(4).setCellValue("Solar Tech Solutions")
        row1.createCell(5).setCellValue("Geração Distribuída")
        row1.createCell(6).setCellValue("Microgeração")
        row1.createCell(7).setCellValue("Disjuntor CC 20A")
        row1.createCell(8).setCellValue(5.5)

        // Data row 2
        val row2 = sheet.createRow(2)
        row2.createCell(0).setCellValue("550e8400-e29b-41d4-a716-446655440002")
        row2.createCell(1).setCellValue("CPFL")
        row2.createCell(2).setCellValue("PROT-2024-002")
        row2.createCell(3).setCellValue("Comercial")
        row2.createCell(4).setCellValue("Solar Tech Solutions")
        row2.createCell(5).setCellValue("Geração Distribuída")
        row2.createCell(6).setCellValue("Minigeração")
        row2.createCell(7).setCellValue("Disjuntor CC 32A")
        row2.createCell(8).setCellValue(15.8)

        // Data row 3
        val row3 = sheet.createRow(3)
        row3.createCell(0).setCellValue("550e8400-e29b-41d4-a716-446655440003")
        row3.createCell(1).setCellValue("Enel")
        row3.createCell(2).setCellValue("PROT-2024-003")
        row3.createCell(3).setCellValue("Industrial")
        row3.createCell(4).setCellValue("Green Energy Corp")
        row3.createCell(5).setCellValue("Geração Distribuída")
        row3.createCell(6).setCellValue("Minigeração")
        row3.createCell(7).setCellValue("Disjuntor CC 50A")
        row3.createCell(8).setCellValue(75.0)

        val file = File("src/test/resources/test-files/$fileName")
        file.parentFile.mkdirs()

        FileOutputStream(file).use { outputStream ->
            workbook.write(outputStream)
        }
        workbook.close()

        return file
    }
}
