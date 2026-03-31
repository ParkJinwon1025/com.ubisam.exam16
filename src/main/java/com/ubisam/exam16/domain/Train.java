package com.ubisam.exam16.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "example_train")
public class Train {

    @Id
    @GeneratedValue
    private Long id;

    // 기차 코드
    private String trainCode;

    // 기차 이름
    private String trainName;

    // 기차 총 좌석 수
    private Integer trainTotalSeats;

    // 여러 개의 기차가 하나의 노선에 운행 가능
    // @ManyToOne
    // private Route route;

}
