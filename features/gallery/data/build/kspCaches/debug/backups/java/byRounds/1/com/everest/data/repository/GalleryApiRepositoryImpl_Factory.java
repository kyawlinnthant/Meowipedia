package com.everest.data.repository;

import com.everest.data.service.GalleryApi;
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
public final class GalleryApiRepositoryImpl_Factory implements Factory<GalleryApiRepositoryImpl> {
  private final Provider<GalleryApi> apiProvider;

  private final Provider<MeowDatabase> dbProvider;

  public GalleryApiRepositoryImpl_Factory(Provider<GalleryApi> apiProvider,
      Provider<MeowDatabase> dbProvider) {
    this.apiProvider = apiProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public GalleryApiRepositoryImpl get() {
    return newInstance(apiProvider.get(), dbProvider.get());
  }

  public static GalleryApiRepositoryImpl_Factory create(Provider<GalleryApi> apiProvider,
      Provider<MeowDatabase> dbProvider) {
    return new GalleryApiRepositoryImpl_Factory(apiProvider, dbProvider);
  }

  public static GalleryApiRepositoryImpl newInstance(GalleryApi api, MeowDatabase db) {
    return new GalleryApiRepositoryImpl(api, db);
  }
}
