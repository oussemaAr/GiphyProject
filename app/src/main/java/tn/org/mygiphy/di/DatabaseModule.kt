package tn.org.mygiphy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tn.org.mygiphy.data.local.AppDatabase
import tn.org.mygiphy.data.local.dao.FavoriteDao


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): FavoriteDao =
        AppDatabase.getInstance(context = context).gifDao()

}