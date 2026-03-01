package projects.core.usecase.address

import org.springframework.stereotype.Service
import projects.core.model.Address
import projects.core.respository.AddressRepository

@Service
class AddressCreateUseCase(
    private val repository: AddressRepository,
) {


    fun execute(address: Address): Address =
        repository.save(address)

}