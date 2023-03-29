package appoutlet.gameoutlet.repository.deals

import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.deals.api.DealResponse

class DealStoreMapper {
    operator fun invoke(dealResponse: DealResponse): Store? {
        val storeId = dealResponse.storeID.toIntOrNull()

        return storeId?.let { id ->
            Store(id = id)
        }
    }
}
