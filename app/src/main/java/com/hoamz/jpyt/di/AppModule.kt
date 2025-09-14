package com.hoamz.jpyt.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.hoamz.jpyt.data.local.UserDao
import com.hoamz.jpyt.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) : UserDatabase{
        return Room.databaseBuilder(context, UserDatabase::class.java,"userdbo")
            .build()
    }

    @Provides
    fun provideUserDao (userDatabase: UserDatabase) : UserDao{
        return userDatabase.userDao()
    }
}