package com.everest.categories.domain.usecase;

import com.everest.categories.data.repository.MeowRepo;
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
public final class SaveMeow_Factory implements Factory<SaveMeow> {
  private final Provider<MeowRepo> meowRepoProvider;

  public SaveMeow_Factory(Provider<MeowRepo> meowRepoProvider) {
    this.meowRepoProvider = meowRepoProvider;
  }

  @Override
  public SaveMeow get() {
    return newInstance(meowRepoProvider.get());
  }

  public static SaveMeow_Factory create(Provider<MeowRepo> meowRepoProvider) {
    return new SaveMeow_Factory(meowRepoProvider);
  }

  public static SaveMeow newInstance(MeowRepo meowRepo) {
    return new SaveMeow(meowRepo);
  }
}
