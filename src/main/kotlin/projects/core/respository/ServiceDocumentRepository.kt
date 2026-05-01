package projects.core.respository

import projects.core.model.ServiceDocument

interface ServiceDocumentRepository {
    fun save(doc: ServiceDocument): ServiceDocument
    fun findAllByServiceId(serviceId: String): List<ServiceDocument>
    fun findById(id: String): ServiceDocument?
    fun deleteById(id: String)
}
