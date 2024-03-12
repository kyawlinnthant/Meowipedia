package com.everest.data.module;

import com.everest.data.service.GalleryApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class GalleryApiModule_ProvidesGalleryApiFactory implements Factory<GalleryApi> {
  private final GalleryApiModule module;

  private final Provider<Retrofit> retrofitProvider;

  public GalleryApiModule_ProvidesGalleryApiFactory(GalleryApiModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public GalleryApi get() {
    return providesGalleryApi(module, retrofitProvider.get());
  }

  public static GalleryApiModule_ProvidesGalleryApiFactory create(GalleryApiModule module,
      Provider<Retrofit> retrofitProvider) {
    return new GalleryApiModule_ProvidesGalleryApiFactory(module, retrofitProvider);
  }

  public static GalleryApi providesGalleryApi(GalleryApiModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.providesGalleryApi(retrofit));
  }
}
