package fr.lleotraas.newsapp.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.lleotraas.newsapp.data.repository.NewsRepositoryImpl
import fr.lleotraas.newsapp.domain.repository.NewsRepository
import fr.lleotraas.newsapp.domain.retrofit.NewsApi
import fr.lleotraas.newsapp.domain.retrofit.utils.Utils
import fr.lleotraas.newsapp.domain.use_cases.GetNews
import fr.lleotraas.newsapp.domain.use_cases.NewsUseCases
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(repository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(repository)
        )
    }

}