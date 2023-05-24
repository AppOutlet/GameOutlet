package appoutlet.gameoutlet.repository.game

import app.cash.sqldelight.Query
import appoutlet.gameoutlet.core.database.GameEntity
import appoutlet.gameoutlet.core.database.GameQueries
import appoutlet.gameoutlet.core.testing.UnitTest
import appoutlet.gameoutlet.domain.Game
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

internal class GameRepositoryTest : UnitTest<GameRepository>() {
    private val mockGameQueries = mockk<GameQueries>(relaxUnitFun = true)
    private val mockGameEntityMapper = mockk<GameEntityMapper>()
    private val mockGameMapper = mockk<GameMapper>()

    override fun buildSut() = GameRepository(
        gameQueries = mockGameQueries,
        gameEntityMapper = mockGameEntityMapper,
        gameMapper = mockGameMapper,
    )

    @Test
    fun `should save game`() {
        val fixtureGame = fixture<Game>()
        val fixtureGameEntity = fixture<GameEntity>()

        every { mockGameEntityMapper.invoke(fixtureGame) } returns fixtureGameEntity

        sut.save(fixtureGame)

        verify { mockGameQueries.save(fixtureGameEntity) }
    }

    @Test
    fun `should find by id`() {
        val fixtureGameId = fixture<Long>()
        val fixtureGameEntity = fixture<GameEntity>()
        val fixtureGame = fixture<Game>()
        val mockGameEntityQuery = mockk<Query<GameEntity>>()

        every { mockGameQueries.findById(fixtureGameId) } returns mockGameEntityQuery
        every { mockGameEntityQuery.executeAsOneOrNull() } returns fixtureGameEntity
        every { mockGameMapper.invoke(fixtureGameEntity) } returns fixtureGame

        val actual = sut.findById(fixtureGameId)

        assertThat(actual).isEqualTo(fixtureGame)
    }

    @Test
    fun `should find by id - null`() {
        val fixtureGameId = fixture<Long>()
        val mockGameEntityQuery = mockk<Query<GameEntity>>()

        every { mockGameQueries.findById(fixtureGameId) } returns mockGameEntityQuery
        every { mockGameEntityQuery.executeAsOneOrNull() } returns null

        val actual = sut.findById(fixtureGameId)

        assertThat(actual).isNull()
    }

    @Test
    fun `should delete by id`() {
        val fixtureGameId = fixture<Long>()

        sut.deleteById(fixtureGameId)

        verify { mockGameQueries.deleteById(fixtureGameId) }
    }

    @Test
    fun `should find all saved games`() {
        val mockGameEntityQuery = mockk<Query<GameEntity>>()
        val fixtureGameEntities = fixture<List<GameEntity>>()
        val fixtureGames = fixture<List<Game>>()

        every { mockGameEntityQuery.executeAsList() } returns fixtureGameEntities
        every { mockGameQueries.findAll() } returns mockGameEntityQuery
        fixtureGameEntities.forEachIndexed { index, gameEntity ->
            every { mockGameMapper.invoke(gameEntity) } returns fixtureGames[index]
        }

        val actual = sut.findAll()

        assertThat(actual).isEqualTo(fixtureGames)
    }
}
