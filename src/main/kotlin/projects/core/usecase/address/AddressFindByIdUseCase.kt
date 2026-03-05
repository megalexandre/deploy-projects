package projects.core.usecase.address

import org.springframework.stereotype.Service
import projects.core.model.Address
import projects.core.respository.AddressRepository

@Service
class AddressFindByIdUseCase(
    private val repository: AddressRepository,
) {

    fun execute(id: String): Address? =
        repository.findById(id)

}