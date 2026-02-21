package projects.infra.fileconverter.converters

import org.springframework.web.multipart.MultipartFile
import projects.infra.fileconverter.FileConverter
import projects.web.projects.port.`in`.ProjectBatchCreateRequest
import projects.web.projects.port.`in`.ProjectCreateRequest

/**
 * Classe abstrata base para converters de arquivos
 * Encapsula a lógica comum de mapeamento de registros para ProjectCreateRequest
 */
abstract class AbstractFileConverter : FileConverter {

    override fun execute(file: MultipartFile): ProjectBatchCreateRequest {
        val records = parseFile(file)
        val projects = records.map { record -> mapRecordToProject(record) }
        return ProjectBatchCreateRequest(projects = projects)
    }

    /**
     * Parse o arquivo e retorna uma lista de registros (mapa de campo -> valor)
     * Cada implementação deve converter o formato específico para uma lista de mapas
     */
    protected abstract fun parseFile(file: MultipartFile): List<Map<String, String?>>

    /**
     * Mapeia um registro (mapa de campo -> valor) para ProjectCreateRequest
     * Usa nomes de campos case-insensitive com fallbacks para variações
     */
    protected fun mapRecordToProject(record: Map<String, String?>): ProjectCreateRequest {
        return ProjectCreateRequest(
            clientId = getField(record, "clienteid", "clientId"),
            utilityCompany = getField(record, "concessionaria"),
            utilityProtocol = getField(record, "protocoloconcessionaria", "protocoloConcessionaria"),
            customerClass = getField(record, "classe"),
            integrator = getField(record, "integrator"),
            modality = getField(record, "modalidade"),
            framework = getField(record, "enquadramento"),
            dcProtection = getField(record, "protecaocc", "protecaoCC"),
            systemPower = getField(record, "potenciasistema", "potenciaSistema")?.toDoubleOrNull()
        )
    }

    /**
     * Busca um campo no mapa com case-insensitive e suporte a múltiplos nomes
     */
    protected fun getField(record: Map<String, String?>, vararg fieldNames: String): String? {
        val lowerCaseRecord = record.entries.associate { (k, v) -> k.lowercase() to v }
        for (fieldName in fieldNames) {
            val value = lowerCaseRecord[fieldName.lowercase()]
            if (value != null) return value
        }
        return null
    }
}
