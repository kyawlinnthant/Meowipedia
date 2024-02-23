package com.everest.categories.data.repository;

import com.everest.categories.data.service.CategoriesService;
import com.everest.database.db.MeowDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.serialization.json.Json;

@ScopeMetadata
@QualifierMetadata("com.everest.dispatcher.DispatcherModule.IoDispatcher")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CategoriesRepoImpl_Factory implements Factory<CategoriesRepoImpl> {
  private final Provider<CategoriesService> categoriesServiceProvider;

  private final Provider<MeowDatabase> meowDatabaseProvider;

  private final Provider<Json> jsonProvider;

  private final Provider<CoroutineDispatcher> ioProvider;

  public CategoriesRepoImpl_Factory(Provider<CategoriesService> categoriesServiceProvider,
      Provider<MeowDatabase> meowDatabaseProvider, Provider<Json> jsonProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    this.categoriesServiceProvider = categoriesServiceProvider;
    this.meowDatabaseProvider = meowDatabaseProvider;
    this.jsonProvider = jsonProvider;
    this.ioProvider = ioProvider;
  }

  @Override
  public CategoriesRepoImpl get() {
    return newInstance(categoriesServiceProvider.get(), meowDatabaseProvider.get(), jsonProvider.get(), ioProvider.get());
  }

  public static CategoriesRepoImpl_Factory create(
      Provider<CategoriesService> categoriesServiceProvider,
      Provider<MeowDatabase> meowDatabaseProvider, Provider<Json> jsonProvider,
      Provider<CoroutineDispatcher> ioProvider) {
    return new CategoriesRepoImpl_Factory(categoriesServiceProvider, meowDatabaseProvider, jsonProvider, ioProvider);
  }

  public static CategoriesRepoImpl newInstance(CategoriesService categoriesService,
      MeowDatabase meowDatabase, Json json, CoroutineDispatcher io) {
    return new CategoriesRepoImpl(categoriesService, meowDatabase, json, io);
  }
}
