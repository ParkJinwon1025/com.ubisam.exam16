package com.ubisam.exam16.api.trains;

import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam16.domain.Train;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainDocs docs;

    @Autowired
    private TrainRepository trainRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // Create
        mockMvc.perform(post("/api/trains").content(docs::newEntity, "KTX-1")).andExpect(is2xx())
                .andDo(result(docs::context, "trainEntity"));

        // Read
        String uri = docs.context("trainEntity", "$._links.self.href");
        mockMvc.perform(get(uri)).andExpect(is2xx());

        // Update
        Map<String, Object> entity = docs.context("trainEntity", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity, "무궁화-1")).andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(uri)).andExpect(is2xx());

    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {

        List<Train> result;
        boolean hasResult;

        List<Train> trainList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainList.add(docs.newEntity("KTX-" + i, "KTX " + i + "호", 10 * i + ""));
        }
        trainRepository.saveAll(trainList);

        // 1. Code 쿼리
        JpaSpecificationBuilder<Train> query = JpaSpecificationBuilder.of(Train.class);
        query.where().and().eq("trainCode", "KTX-20");
        result = trainRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "KTX-20".equals(u.getTrainCode()));
        assertEquals(true, hasResult);

        // 2. Name 쿼리
        JpaSpecificationBuilder<Train> query1 = JpaSpecificationBuilder.of(Train.class);
        query1.where().and().eq("trainName", "KTX 23호");
        result = trainRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "KTX 23호".equals(u.getTrainName()));
        assertEquals(true, hasResult);

        // 3. totalSeats 쿼리
        JpaSpecificationBuilder<Train> query2 = JpaSpecificationBuilder.of(Train.class);
        query2.where().and().eq("trainTotalSeats", 40);
        result = trainRepository.findAll(query2.build());
        hasResult = result.stream().anyMatch(u -> 40 == u.getTrainTotalSeats());
        assertEquals(true, hasResult);
    }

    // Search
    @Test
    public void contextLoads2() throws Exception {

        List<Train> trainList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainList.add(docs.newEntity("KTX-" + i, "KTX " + i + "호", 10 * i + ""));
        }
        trainRepository.saveAll(trainList);

        // 1. Code로 검색
        mockMvc.perform(get("/api/trains/search/findByTrainCode").param("trainCode", "KTX-12")).andExpect(is2xx());

        // 2. Name으로 검색
        mockMvc.perform(get("/api/trains/search/findByTrainName").param("trainName", "KTX 30호")).andExpect(is2xx());

        // 3. totalSeats으로 검색
        mockMvc.perform(get("/api/trains/search/findByTrainTotalSeats").param("trainTotalSeats", "100"))
                .andExpect(is2xx());

        // 4. 페이지네이션 - 5개씩 8페이지
        mockMvc.perform(get("/api/trains").param("size", "5")).andExpect(is2xx());

        // 5. 정렬- trainName,desc
        mockMvc.perform(get("/api/trains").param("sort", "trainName,desc"));

    }
}
