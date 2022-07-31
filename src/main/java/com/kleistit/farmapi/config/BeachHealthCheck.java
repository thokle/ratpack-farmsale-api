package com.kleistit.farmapi.config;

import ratpack.core.health.HealthCheck;
import ratpack.exec.Promise;
import ratpack.exec.registry.Registry;

public class BeachHealthCheck implements HealthCheck {
    @Override
    public String getName() {
        return "beach-api";
    }

    @Override
    public Promise<Result> check(Registry registry) throws Exception {
        return  Promise.value(Result.healthy());
    }
}
