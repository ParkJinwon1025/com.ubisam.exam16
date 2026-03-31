package com.ubisam.exam16.api.trains;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam16.domain.Train;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class TrainDocs extends MockMvcRestDocs {

    public Train newEntity(String... entity) {
        Train train = new Train();
        train.setTrainCode(entity.length > 0 ? entity[0] : super.randomText("trainCode"));
        train.setTrainName(entity.length > 1 ? entity[1] : super.randomText("trainName"));
        train.setTrainTotalSeats(entity.length > 2 ? Integer.valueOf(entity[2]) : super.randomInt());
        return train;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String code) {

        entity.put("trainCode", code);
        return entity;

    }

}
