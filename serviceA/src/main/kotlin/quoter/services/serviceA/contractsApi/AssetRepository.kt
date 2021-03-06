package quoter.services.serviceA.contractsApi

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import quoter.services.model.Asset

@Repository
interface AssetRepository : JpaRepository<Asset, String>