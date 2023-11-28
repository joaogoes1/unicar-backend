package com.unicar.ride.di;

import com.google.inject.AbstractModule;
import com.unicar.ride.data.datasource.RideDataSource;
import com.unicar.ride.domain.service.RideService;
import com.unicar.ride.domain.service.RideServiceImpl;

public class RideModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RideService.class).to(RideServiceImpl.class);
        bind(RideDataSource.class).to(RideDataSource.class);
    }
}
