package appoutlet.gameoutlet.repository.store

import app.cash.sqldelight.Query
import appoutlet.gameoutlet.core.database.StoreEntity
import appoutlet.gameoutlet.core.database.StoreQueries
import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.repository.store.api.StoreApi
import appoutlet.gameoutlet.repository.store.api.model.StoreResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class StoreRepositoryTest : UnitTest<StoreRepository>() {
    private val mockStoreQueries = mockk<StoreQueries>(relaxUnitFun = true)
    private val mockStoreApi = mockk<StoreApi>()
    private val mockStoreCacheRepository = mockk<StoreCacheRepository>(relaxUnitFun = true)
    private val mockStoreMapper = mockk<StoreMapper>()
    private val mockStoreEntityMapper = mockk<StoreEntityMapper>()

    override fun buildSut() = StoreRepository(
        storeQueries = mockStoreQueries,
        storeApi = mockStoreApi,
        storeCacheRepository = mockStoreCacheRepository,
        storeMapper = mockStoreMapper,
        storeEntityMapper = mockStoreEntityMapper,
    )

    @Test
    fun `should find all from cache`() = runTest {
        val mockQueryStoreEntity = mockk<Query<StoreEntity>>()
        val fixtureStoreEntityList = fixture<List<StoreEntity>>()
        val fixtureStoreList = fixture<List<Store>>()

        every { mockStoreCacheRepository.isStoreListCacheValid() } returns true
        every { mockStoreQueries.findAll() } returns mockQueryStoreEntity
        every { mockQueryStoreEntity.executeAsList() } returns fixtureStoreEntityList
        fixtureStoreEntityList.forEachIndexed { index, entity ->
            every { mockStoreMapper.invoke(entity) } returns fixtureStoreList[index]
        }

        val actual = sut.findAll()

        assertThat(actual).isEqualTo(fixtureStoreList)
    }

    @Test
    fun `should find all from api`() = runTest {
        val fixtureStoreResponseList = listOf(
            fixture<StoreResponse>().copy(isActive = 1),
            fixture<StoreResponse>().copy(isActive = 1),
            fixture<StoreResponse>().copy(isActive = 1),
            fixture<StoreResponse>().copy(isActive = 1),
            fixture<StoreResponse>().copy(isActive = 1),
        )
        val fixtureStoreList = fixture<List<Store>>()
        val fixtureStoreEntityList = fixture<List<StoreEntity>>()

        every { mockStoreCacheRepository.isStoreListCacheValid() } returns false
        coEvery { mockStoreApi.findAll() } returns fixtureStoreResponseList
        fixtureStoreResponseList.forEachIndexed { index, storeResponse ->
            every { mockStoreMapper.invoke(storeResponse) } returns fixtureStoreList[index]
        }
        fixtureStoreList.forEachIndexed { index, store ->
            every { mockStoreEntityMapper.invoke(store) } returns fixtureStoreEntityList[index]
        }

        val actual = sut.findAll()

        assertThat(actual).isEqualTo(fixtureStoreList)

        fixtureStoreEntityList.forEach {
            verify { mockStoreQueries.save(it) }
        }
        verify { mockStoreCacheRepository.registerStoreListCached() }
    }

    @Test
    fun `should find all from api and filter inactive`() = runTest {
        val fixtureStoreResponseActive = fixture<StoreResponse>().copy(isActive = 1)
        val fixtureStoreResponseInactive = fixture<StoreResponse>().copy(isActive = 0)
        val fixtureStoreResponseList = listOf(fixtureStoreResponseActive, fixtureStoreResponseInactive,)
        val fixtureStoreList = fixture<List<Store>>()
        val fixtureStoreEntityList = fixture<List<StoreEntity>>()

        every { mockStoreCacheRepository.isStoreListCacheValid() } returns false
        coEvery { mockStoreApi.findAll() } returns fixtureStoreResponseList
        every { mockStoreMapper.invoke(fixtureStoreResponseActive) } returns fixtureStoreList.first()
        every { mockStoreEntityMapper.invoke(fixtureStoreList.first()) } returns fixtureStoreEntityList.first()

        val actual = sut.findAll()

        assertThat(actual).isEqualTo(listOf(fixtureStoreList.first()))

        verify { mockStoreQueries.save(fixtureStoreEntityList.first()) }
        verify { mockStoreCacheRepository.registerStoreListCached() }
    }
}
