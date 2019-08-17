package quoter.services.serviceA.repositories
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import quoter.services.model.Contract

@Repository
interface ContractRepository : JpaRepository<Contract, String>