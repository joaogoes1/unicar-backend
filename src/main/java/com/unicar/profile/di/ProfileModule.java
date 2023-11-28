package com.unicar.profile.di;

import com.google.inject.AbstractModule;
import com.unicar.profile.data.datasource.FirebaseProfileDataSource;
import com.unicar.profile.data.datasource.ProfileDataSource;
import com.unicar.profile.domain.service.ProfileService;
import com.unicar.profile.domain.service.ProfileServiceImpl;

public class ProfileModule extends AbstractModule {

    @Override
    public void configure() {
        bind(ProfileDataSource.class).to(FirebaseProfileDataSource.class);
        bind(ProfileService.class).to(ProfileServiceImpl.class);
    }
}