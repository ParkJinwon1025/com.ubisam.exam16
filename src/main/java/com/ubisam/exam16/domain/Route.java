package com.ubisam.exam16.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

// 노선
@Data
@Entity
@Table(name = "example_route")
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    // 노선 코드
    private String routeCode;

    // 노선 이름
    private String routeName;

    // 예상 소요 시간(분)
    private Integer routeEstimatedTime;

    // 하나의 노선에는 여러 개의 기차가 운행 가능
    // @OneToMany
    // private List<Train> trains;

    @Transient
    private String searchCode;

    @Transient
    private String searchName;
}
