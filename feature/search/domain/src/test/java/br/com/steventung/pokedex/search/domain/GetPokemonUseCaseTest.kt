package br.com.steventung.pokedex.search.domain

import br.com.steventung.pokedex.common.utils.NetworkResult
import br.com.steventung.pokedex.network.model.PokemonAbility
import br.com.steventung.pokedex.network.model.PokemonSprites
import br.com.steventung.pokedex.network.model.PokemonStats
import br.com.steventung.pokedex.search.domain.model.PokemonDomain
import br.com.steventung.pokedex.search.domain.model.Types
import br.com.steventung.pokedex.search.domain.usecase.GetPokemonUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonUseCaseTest {

    private lateinit var getPokemonUseCase: GetPokemonUseCase
    private lateinit var fakePokemonRepository: FakePokemonRepository

    private val pokemon1 = PokemonDomain(
        id = 1,
        name = "Bulbasaur",
        weight = 69,
        height = 7,
        image = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "bulbasaur_image"))),
        types = listOf(Types.Grass),
        stats = listOf(PokemonStats(45, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("bulbasaur_ability")
            )
        )
    )
    private val pokemon2 = PokemonDomain(
        id = 2,
        name = "Ivysaur",
        weight = 130,
        height = 10,
        image = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "ivysaur_image"))),
        types = listOf(
            Types.Grass,
            Types.Poison
        ),
        stats = listOf(PokemonStats(60, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("ivysaur_ability")
            )
        )
    )
    private val pokemon3 = PokemonDomain(
        id = 3,
        name = "Venusaur",
        weight = 1000,
        height = 20,
        image = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "venusaur_image"))),
        types = listOf(
            Types.Grass,
            Types.Poison
        ),
        stats = listOf(PokemonStats(80, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("venusaur_ability")
            )
        )
    )
    private val pokemon4 = PokemonDomain(
        id = 4,
        name = "Charmander",
        weight = 85,
        height = 6,
        image = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "charmander_image"))),
        types = listOf(Types.Fire),
        stats = listOf(PokemonStats(39, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("charmander_ability")
            )
        )
    )
    private val pokemon5 = PokemonDomain(
        id = 5,
        name = "Charmeleon",
        weight = 190,
        height = 11,
        image = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "charmeleon_image"))),
        types = listOf(Types.Fire),
        stats = listOf(PokemonStats(58, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("charmeleon_ability")
            )
        )
    )
    private val pokemon6 = PokemonDomain(
        id = 6,
        name = "Charizard",
        weight = 905,
        height = 17,
        image = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "charizard_image"))),
        types = listOf(Types.Fire, Types.Flying),
        stats = listOf(PokemonStats(78, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("charizard_ability")
            )
        )
    )

    @Before
    fun setUp() {
        fakePokemonRepository = FakePokemonRepository()
        getPokemonUseCase = GetPokemonUseCase(fakePokemonRepository)
    }

    @Test
    fun `Passing valid id returns expected Pokemon`() = runBlocking {
        val pokemon = getPokemonUseCase(4).first()
        assertThat(pokemon).isEqualTo(NetworkResult.Success(pokemon4))
    }

    @Test
    fun `Passing invalid id returns error`() = runBlocking {
        val pokemon = getPokemonUseCase(-10).first()
        assertThat(pokemon).isInstanceOf(NetworkResult.Error::class.java)
    }
}