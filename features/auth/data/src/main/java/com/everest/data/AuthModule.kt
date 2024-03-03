package com.everest.data

import com.everest.data.repo.SignInRepo
import com.everest.data.repo.SignInRepoImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface AuthModule {
    @Binds
    @Singleton
    fun provideSignInRepoImpl(repo: SignInRepoImpl): SignInRepo
}

@Module
@InstallIn(ViewModelComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

}
