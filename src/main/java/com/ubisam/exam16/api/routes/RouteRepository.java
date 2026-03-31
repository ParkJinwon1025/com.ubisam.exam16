package com.ubisam.exam16.api.routes;

import com.ubisam.exam16.domain.Route;

import io.u2ware.common.data.jpa.repository.RestfulJpaRepository;

// RestFulRepository 사용
public interface RouteRepository extends RestfulJpaRepository<Route, Long> {

}
