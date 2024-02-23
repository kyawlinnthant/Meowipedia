package com.everest.categories.data.repository;

import com.everest.database.dao.MeowDao;
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
public final class MewoDbRepoImpl_Factory implements Factory<MewoDbRepoImpl> {
  private final Provider<MeowDao> meowDaoProvider;

  public MewoDbRepoImpl_Factory(Provider<MeowDao> meowDaoProvider) {
    this.meowDaoProvider = meowDaoProvider;
  }

  @Override
  public MewoDbRepoImpl get() {
    return newInstance(meowDaoProvider.get());
  }

  public static MewoDbRepoImpl_Factory create(Provider<MeowDao> meowDaoProvider) {
    return new MewoDbRepoImpl_Factory(meowDaoProvider);
  }

  public static MewoDbRepoImpl newInstance(MeowDao meowDao) {
    return new MewoDbRepoImpl(meowDao);
  }
}
