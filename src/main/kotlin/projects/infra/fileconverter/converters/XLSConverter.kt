package projects.infra.fileconverter.converters

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class XLSConverter : AbstractFileConverter() {

    override fun supports(): List<String> {
        return listOf("xls", "xlsx")
    }

    override fun parseFile(file: MultipartFile): List<Map<String, String?>> {
        val workbook = WorkbookFactory.create(file.inputStream)

        return workbook.use { wb ->
            val sheet = wb.getSheetAt(0)
            val headerRow = sheet.getRow(0)

            val headers = headerRow.cellIterator().asSequence()
                .map { it.columnIndex to it.stringCellValue }
                .toList()

            sheet.drop(1)
                .filter { it != null && it.getCell(0) != null }
                .map { row ->
                    headers.associate { (columnIndex, headerName) ->
                        headerName to getCellValue(row, columnIndex)
                    }
                }
                .toList()
        }
    }

    private fun getCellValue(row: Row, columnIndex: Int): String? {
        val cell = row.getCell(columnIndex) ?: return null

        return when (cell.cellType) {
            CellType.STRING -> cell.stringCellValue
            CellType.NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    cell.localDateTimeCellValue.toString()
                } else {
                    val numValue = cell.numericCellValue
                    if (numValue == numValue.toLong().toDouble()) {
                        numValue.toLong().toString()
                    } else {
                        numValue.toString()
                    }
                }
            }
            CellType.BOOLEAN -> cell.booleanCellValue.toString()
            CellType.FORMULA -> {
                // Tenta avaliar a fórmula
                try {
                    cell.stringCellValue
                } catch (_: Exception) {
                    cell.numericCellValue.toString()
                }
            }
            else -> null
        }
    }
}
