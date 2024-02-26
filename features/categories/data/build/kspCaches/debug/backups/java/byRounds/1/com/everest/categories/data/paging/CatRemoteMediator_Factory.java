package com.everest.categories.data.paging;

import com.everest.categories.data.service.CategoriesService;
import com.everest.database.db.MeowDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class CatRemoteMediator_Factory implements Factory<CatRemoteMediator> {
  private final Provider<CategoriesService> categoriesServiceProvider;

  private final Provider<MeowDatabase> meowDatabaseProvider;

  public CatRemoteMediator_Factory(Provider<CategoriesService> categoriesServiceProvider,
      Provider<MeowDatabase> meowDatabaseProvider) {
    this.categoriesServiceProvider = categoriesServiceProvider;
    this.meowDatabaseProvider = meowDatabaseProvider;
  }

  @Override
  public CatRemoteMediator get() {
    return newInstance(categoriesServiceProvider.get(), meowDatabaseProvider.get());
  }

  public static CatRemoteMediator_Factory create(
      Provider<CategoriesService> categoriesServiceProvider,
      Provider<MeowDatabase> meowDatabaseProvider) {
    return new CatRemoteMediator_Factory(categoriesServiceProvider, meowDatabaseProvider);
  }

  public static CatRemoteMediator newInstance(CategoriesService categoriesService,
      MeowDatabase meowDatabase) {
    return new CatRemoteMediator(categoriesService, meowDatabase);
  }
}
