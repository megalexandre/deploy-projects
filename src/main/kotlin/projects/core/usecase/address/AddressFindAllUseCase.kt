package projects.core.usecase.address

import org.springframework.stereotype.Service
import projects.core.model.Address
import projects.core.respository.AddressRepository

@Service
class AddressFindAllUseCase(
    private val repository: AddressRepository,
) {

    fun execute(): List<Address> =
        repository.findAll()

}