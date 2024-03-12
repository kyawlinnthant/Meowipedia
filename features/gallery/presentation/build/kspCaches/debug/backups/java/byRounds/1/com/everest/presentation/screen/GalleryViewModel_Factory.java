package com.everest.presentation.screen;

import com.everest.domain.usecase.GetGalleries;
import com.everest.navigation.navigator.AppNavigator;
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
public final class GalleryViewModel_Factory implements Factory<GalleryViewModel> {
  private final Provider<GetGalleries> getGalleriesProvider;

  private final Provider<AppNavigator> appNavigatorProvider;

  public GalleryViewModel_Factory(Provider<GetGalleries> getGalleriesProvider,
      Provider<AppNavigator> appNavigatorProvider) {
    this.getGalleriesProvider = getGalleriesProvider;
    this.appNavigatorProvider = appNavigatorProvider;
  }

  @Override
  public GalleryViewModel get() {
    return newInstance(getGalleriesProvider.get(), appNavigatorProvider.get());
  }

  public static GalleryViewModel_Factory create(Provider<GetGalleries> getGalleriesProvider,
      Provider<AppNavigator> appNavigatorProvider) {
    return new GalleryViewModel_Factory(getGalleriesProvider, appNavigatorProvider);
  }

  public static GalleryViewModel newInstance(GetGalleries getGalleries, AppNavigator appNavigator) {
    return new GalleryViewModel(getGalleries, appNavigator);
  }
}
