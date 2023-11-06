package com.github.chMatvey.plugins

import com.github.chMatvey.dao.DroneDAOFacade
import com.github.chMatvey.dao.impl.DroneDAOFacadeImpl
import com.github.chMatvey.services.DroneService
import com.github.chMatvey.services.impl.DatabaseDroneService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::DroneDAOFacadeImpl) { bind<DroneDAOFacade>() }
    singleOf(::DatabaseDroneService) { bind<DroneService>() }
}