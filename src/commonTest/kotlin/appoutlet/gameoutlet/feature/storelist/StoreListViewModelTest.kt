package appoutlet.gameoutlet.feature.storelist

import appoutlet.gameoutlet.core.testing.ViewModelTest
import appoutlet.gameoutlet.domain.Store
import appoutlet.gameoutlet.feature.store.StoreView
import appoutlet.gameoutlet.repository.store.StoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StoreListViewModelTest : ViewModelTest<StoreListViewModel>() {
    private val mockStoreRepository = mockk<StoreRepository>()
    private val mockStoreListUiModelMapper = mockk<StoreListUiModelMapper>()
    private val mockStoreViewProvider = mockk<StoreView.Provider>()

    override fun buildSut() = StoreListViewModel(
        storeRepository = mockStoreRepository,
        storeListUiModelMapper = mockStoreListUiModelMapper,
        storeViewProvider = mockStoreViewProvider,
    )

    @Test
    fun `should load stores`() = runViewModelTest {
        val fixtureEntities = fixture<List<Store>>()
        val fixtureUiModels = fixture<List<StoreUiModel>>()

        coEvery { mockStoreRepository.findAll() } returns fixtureEntities
        every { mockStoreListUiModelMapper.invoke(fixtureEntities) } returns fixtureUiModels

        sut.onInputEvent(StoreListInputEvent.Load)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(StoreListUiState.Loaded(fixtureUiModels))
    }

    @Test
    fun `should load stores - error state`() = runViewModelTest {
        coEvery { mockStoreRepository.findAll() } throws RuntimeException()

        sut.onInputEvent(StoreListInputEvent.Load)

        advanceUntilIdle()

        assertThat(sut.uiState.value).isEqualTo(StoreListUiState.Error)
    }

    @Test
    fun `should select stores`() = runViewModelTest {
        val fixtureStore = fixture<Store>()
        val mockStoreView = mockk<StoreView>()

        every { mockStoreViewProvider.getStoreView(fixtureStore) } returns mockStoreView

        sut.onInputEvent(StoreListInputEvent.SelectStore(fixtureStore))

        verify { mockNavigator.push(mockStoreView) }
    }
}
