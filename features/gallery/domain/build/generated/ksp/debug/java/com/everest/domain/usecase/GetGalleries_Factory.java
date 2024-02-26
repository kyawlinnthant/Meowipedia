package com.everest.domain.usecase;

import com.everest.data.repository.GalleryApiRepository;
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
public final class GetGalleries_Factory implements Factory<GetGalleries> {
  private final Provider<GalleryApiRepository> repoProvider;

  public GetGalleries_Factory(Provider<GalleryApiRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetGalleries get() {
    return newInstance(repoProvider.get());
  }

  public static GetGalleries_Factory create(Provider<GalleryApiRepository> repoProvider) {
    return new GetGalleries_Factory(repoProvider);
  }

  public static GetGalleries newInstance(GalleryApiRepository repo) {
    return new GetGalleries(repo);
  }
}
