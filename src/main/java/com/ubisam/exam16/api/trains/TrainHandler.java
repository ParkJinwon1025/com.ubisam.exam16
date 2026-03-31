package com.ubisam.exam16.api.trains;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam16.domain.Train;

@Component
@RepositoryEventHandler
public class TrainHandler {

    @HandleBeforeCreate
    public void HandleBeforeCreate(Train e) throws Exception {
        // 로그 코드 작성
        // 테스트에서는 System.out.println 사용 / 실무에서는 LOG 사용
        System.out.println("[HanldeBeforeCreate]" + e);

    }

    @HandleAfterCreate
    public void afterCreate(Train e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterCreate]" + e);

    }

    @HandleBeforeSave
    public void beforeSave(Train e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeSave]" + e);

    }

    @HandleAfterSave
    public void afterSave(Train e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + e);

    }

    @HandleBeforeDelete
    public void beforeDelete(Train e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeDelete]" + e);

    }

    @HandleAfterDelete
    public void afterDelete(Train e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + e);

    }

}
