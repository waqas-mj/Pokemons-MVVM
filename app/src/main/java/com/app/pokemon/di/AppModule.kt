package com.app.pokemon.di

import com.app.pokemon.data.api.PokemonApiService
import com.app.pokemon.data.remotedatasource.PokemonRemoteDataSource
import com.app.pokemon.data.repository.PokemonRepositoryImpl
import com.app.pokemon.domain.repository.PokemonRepository
import com.app.pokemon.domain.usecase.FetchPokemonsUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: PokemonApiService): PokemonRemoteDataSource {
        return PokemonRemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(pokemonRemoteDataSource: PokemonRemoteDataSource): PokemonRepository {
        return PokemonRepositoryImpl(pokemonRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providesUseCase(pokemonRepository: PokemonRepository): FetchPokemonsUseCase {
        return FetchPokemonsUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }


}