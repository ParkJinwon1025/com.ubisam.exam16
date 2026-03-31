package com.ubisam.exam16.api.routes;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam16.domain.Route;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class RouteTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RouteDocs docs;

    @Autowired
    private RouteRepository routeRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // Create
        mockMvc.perform(post("/api/routes").content(docs::newEntity, "route1")).andExpect(is2xx())
                .andDo(result(docs::context, "routeEntity"));

        // Read
        String uri = docs.context("routeEntity", "$._links.self.href");
        mockMvc.perform(post(uri)).andExpect(is2xx());

        // Update
        Map<String, Object> entity = docs.context("routeEntity", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity, "route11")).andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(uri)).andExpect(is2xx());

    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {

        List<Route> result;
        boolean hasResult;

        List<Route> routeList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            routeList.add(docs.newEntity("route" + i, "경춘선" + i, 3 * i + ""));
        }
        routeRepository.saveAll(routeList);

        // 1. Code Query
        JpaSpecificationBuilder<Route> query = JpaSpecificationBuilder.of(Route.class);
        query.where().and().eq("routeCode", "route11");
        result = routeRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "route11".equals(u.getRouteCode()));
        assertEquals(true, hasResult);

        // 2. Name Query
        JpaSpecificationBuilder<Route> query1 = JpaSpecificationBuilder.of(Route.class);
        query1.where().and().eq("routeName", "경춘선4");
        result = routeRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "경춘선4".equals(u.getRouteName()));
        assertEquals(true, hasResult);

        // 3. EstimatedTime Query
        JpaSpecificationBuilder<Route> query2 = JpaSpecificationBuilder.of(Route.class);
        query2.where().and().eq("routeEstimatedTime", 6);
        result = routeRepository.findAll(query2.build());
        hasResult = result.stream().anyMatch(u -> 6 == u.getRouteEstimatedTime());
        assertEquals(true, hasResult);

    }

    // Search
    @Test
    public void contextLoads2() throws Exception {

        List<Route> routeList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            routeList.add(docs.newEntity("route" + i, "경춘선" + i, 3 * i + ""));
        }
        routeRepository.saveAll(routeList);

        String uri = "/api/routes/search";

        // 1. routeCode로 Search
        mockMvc.perform(post(uri).content(docs::setSearchCode, "route14")).andExpect(is2xx());

        // 2. routeName으로 Search
        mockMvc.perform(post(uri).content(docs::setSearchName, "경춘선4")).andExpect(is2xx());

        // 3. 페이지네이션 - 10개씩 2페이지
        mockMvc.perform(post(uri).param("size", "10")).andDo(print()).andExpect(is2xx());

        // 4. 정렬 - routeCode,desc
        mockMvc.perform(post(uri).param("sort", "routeCode,desc")).andDo(print()).andExpect(is2xx());

    }

}
