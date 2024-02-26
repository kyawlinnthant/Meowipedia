package com.everest.categories.domain.usecase;

import com.everest.categories.data.repository.CategoriesRepo;
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
public final class FetchCategories_Factory implements Factory<FetchCategories> {
  private final Provider<CategoriesRepo> categoryRepoProvider;

  public FetchCategories_Factory(Provider<CategoriesRepo> categoryRepoProvider) {
    this.categoryRepoProvider = categoryRepoProvider;
  }

  @Override
  public FetchCategories get() {
    return newInstance(categoryRepoProvider.get());
  }

  public static FetchCategories_Factory create(Provider<CategoriesRepo> categoryRepoProvider) {
    return new FetchCategories_Factory(categoryRepoProvider);
  }

  public static FetchCategories newInstance(CategoriesRepo categoryRepo) {
    return new FetchCategories(categoryRepo);
  }
}
