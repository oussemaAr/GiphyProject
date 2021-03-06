package tn.org.mygiphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tn.org.mygiphy.data.local.FavoriteRepository
import tn.org.mygiphy.data.local.dao.FavoriteDao
import tn.org.mygiphy.data.remote.api.GiphyService
import tn.org.mygiphy.data.remote.repository.GiphyRepository


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideGiphyRepository(
        service: GiphyService,
        repository: FavoriteRepository
    ): GiphyRepository = GiphyRepository(service, repository)

    @Provides
    fun provideFavoriteRepository(favoriteDao: FavoriteDao): FavoriteRepository =
        FavoriteRepository(favoriteDao)
}