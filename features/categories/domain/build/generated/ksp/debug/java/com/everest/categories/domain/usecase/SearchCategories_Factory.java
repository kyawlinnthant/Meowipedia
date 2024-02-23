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
public final class SearchCategories_Factory implements Factory<SearchCategories> {
  private final Provider<CategoriesRepo> categoriesRepoProvider;

  public SearchCategories_Factory(Provider<CategoriesRepo> categoriesRepoProvider) {
    this.categoriesRepoProvider = categoriesRepoProvider;
  }

  @Override
  public SearchCategories get() {
    return newInstance(categoriesRepoProvider.get());
  }

  public static SearchCategories_Factory create(Provider<CategoriesRepo> categoriesRepoProvider) {
    return new SearchCategories_Factory(categoriesRepoProvider);
  }

  public static SearchCategories newInstance(CategoriesRepo categoriesRepo) {
    return new SearchCategories(categoriesRepo);
  }
}
