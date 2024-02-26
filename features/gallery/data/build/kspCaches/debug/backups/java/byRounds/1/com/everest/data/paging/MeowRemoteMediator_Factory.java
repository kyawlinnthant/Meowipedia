package com.everest.data.paging;

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
public final class MeowRemoteMediator_Factory implements Factory<MeowRemoteMediator> {
  private final Provider<GalleryApi> apiProvider;

  private final Provider<MeowDatabase> dbProvider;

  public MeowRemoteMediator_Factory(Provider<GalleryApi> apiProvider,
      Provider<MeowDatabase> dbProvider) {
    this.apiProvider = apiProvider;
    this.dbProvider = dbProvider;
  }

  @Override
  public MeowRemoteMediator get() {
    return newInstance(apiProvider.get(), dbProvider.get());
  }

  public static MeowRemoteMediator_Factory create(Provider<GalleryApi> apiProvider,
      Provider<MeowDatabase> dbProvider) {
    return new MeowRemoteMediator_Factory(apiProvider, dbProvider);
  }

  public static MeowRemoteMediator newInstance(GalleryApi api, MeowDatabase db) {
    return new MeowRemoteMediator(api, db);
  }
}
