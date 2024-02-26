package com.everest.categories.presentation.categories;

import com.everest.categories.domain.usecase.FetchCategories;
import com.everest.categories.domain.usecase.SaveMeow;
import com.everest.categories.domain.usecase.SearchCategories;
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
public final class CategoriesViewModel_Factory implements Factory<CategoriesViewModel> {
  private final Provider<FetchCategories> fetchCategoriesProvider;

  private final Provider<SearchCategories> searchCategoriesProvider;

  private final Provider<SaveMeow> saveMeowProvider;

  private final Provider<AppNavigator> navigatorProvider;

  public CategoriesViewModel_Factory(Provider<FetchCategories> fetchCategoriesProvider,
      Provider<SearchCategories> searchCategoriesProvider, Provider<SaveMeow> saveMeowProvider,
      Provider<AppNavigator> navigatorProvider) {
    this.fetchCategoriesProvider = fetchCategoriesProvider;
    this.searchCategoriesProvider = searchCategoriesProvider;
    this.saveMeowProvider = saveMeowProvider;
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public CategoriesViewModel get() {
    return newInstance(fetchCategoriesProvider.get(), searchCategoriesProvider.get(), saveMeowProvider.get(), navigatorProvider.get());
  }

  public static CategoriesViewModel_Factory create(
      Provider<FetchCategories> fetchCategoriesProvider,
      Provider<SearchCategories> searchCategoriesProvider, Provider<SaveMeow> saveMeowProvider,
      Provider<AppNavigator> navigatorProvider) {
    return new CategoriesViewModel_Factory(fetchCategoriesProvider, searchCategoriesProvider, saveMeowProvider, navigatorProvider);
  }

  public static CategoriesViewModel newInstance(FetchCategories fetchCategories,
      SearchCategories searchCategories, SaveMeow saveMeow, AppNavigator navigator) {
    return new CategoriesViewModel(fetchCategories, searchCategories, saveMeow, navigator);
  }
}
