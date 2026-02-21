package projects.infra.fileconverter.converters

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class CVSConverter : AbstractFileConverter() {

    override fun supports(): List<String> {
        return listOf("csv")
    }

    override fun parseFile(file: MultipartFile): List<Map<String, String?>> {
        return CSVParser.parse(
            file.inputStream.bufferedReader(),
            CSVFormat.Builder.create()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build()
        ).use { parser ->
            parser.records.map { record ->
                record.toMap()
            }
        }
    }
}