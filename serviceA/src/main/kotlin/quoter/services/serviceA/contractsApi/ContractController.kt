package quoter.services.serviceA.contractsApi

import org.springframework.web.bind.annotation.*
import quoter.services.model.Contract
import quoter.services.model.Quote
import quoter.services.serviceA.pricers.PricingService
import sun.invoke.empty.Empty
import java.util.*

@RestController
@RequestMapping("contracts")
class ContractController(
        private val contractRepository: ContractRepository,
        private val pricingService: PricingService
) {

    @GetMapping
    suspend fun findAll(): Iterable<Contract> {
        return contractRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): Optional<Contract> {
        return contractRepository.findById(id)
    }

    @GetMapping("/{id}/quote")
    fun priceContractById(@PathVariable("id") id: String): Quote {
        val contract = contractRepository.findById(id).get()
        return pricingService.priceContract(contract)
    }

    @PostMapping()
    fun save(@RequestBody contract: Contract): String {
        contractRepository.save(contract)
        return "contract saved successfully: $contract"
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): String {
        val res = contractRepository.findById(id)
        return if (res is Empty) "contract not found" else "contract $id deleted"
    }

}