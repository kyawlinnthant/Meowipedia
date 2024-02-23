package com.everest.categories.data.module;

import com.everest.categories.data.service.CategoriesService;
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
public final class CategoryModule_ProvideCategoriesServiceFactory implements Factory<CategoriesService> {
  private final Provider<Retrofit> retrofitProvider;

  public CategoryModule_ProvideCategoriesServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CategoriesService get() {
    return provideCategoriesService(retrofitProvider.get());
  }

  public static CategoryModule_ProvideCategoriesServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new CategoryModule_ProvideCategoriesServiceFactory(retrofitProvider);
  }

  public static CategoriesService provideCategoriesService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(CategoryModule.INSTANCE.provideCategoriesService(retrofit));
  }
}
