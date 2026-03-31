package com.ubisam.exam16.api.trains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ubisam.exam16.domain.Train;
import java.util.List;

// Train 도메인만 JpaRepository 사용 / 실무에서는 RestFulRepository를 사용 
public interface TrainRepository extends JpaRepository<Train, Long>, JpaSpecificationExecutor<Train> {

    List<Train> findByTrainCode(String trainCode);

    List<Train> findByTrainName(String trainName);

    List<Train> findByTrainTotalSeats(Integer trainTotalSeats);

}
