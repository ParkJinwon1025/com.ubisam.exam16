package com.ubisam.exam16.api.routes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam16.domain.Route;
import com.ubisam.exam16.domain.Train;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class RouteDocs extends MockMvcRestDocs {

    public Route newEntity(String... entity) {
        Route route = new Route();
        route.setRouteCode(entity.length > 0 ? entity[0] : super.randomText("routeCode"));
        route.setRouteName(entity.length > 1 ? entity[1] : super.randomText("routeName"));
        route.setRouteEstimatedTime(entity.length > 2 ? Integer.valueOf(entity[2]) : super.randomInt());
        return route;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String code) {

        entity.put("routeCode", code);
        return entity;

    }

    public Map<String, Object> setSearchCode(String code) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchCode", code);
        return entity;
    }

    public Map<String, Object> setSearchName(String name) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchName", name);
        return entity;
    }

}
