package com.ubisam.exam16.api.routes;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam16.domain.Route;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class RouteHandler {

    @HandleBeforeRead
    public void handleBeforeRead(Route e, Specification<Route> spec) throws Exception {

        // 로그 코드 작성
        // 테스트에서는 System.out.println() 사용 / 실무에서는 LOG 사용
        System.out.println("[HandleBeforeRead]" + e);
        JpaSpecificationBuilder<Route> query = JpaSpecificationBuilder.of(Route.class);
        query.where()
                .and().eq("routeName", e.getSearchName())
                .and().eq("routeCode", e.getSearchCode()).build(spec);

    }

    @HandleAfterRead
    public void afterRead(Route e, Serializable r) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterRead] " + e);
        System.out.println("[HandleafterRead] " + r);
    }

    @HandleBeforeCreate
    public void beforeCreate(Route e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeCreate] " + e);
    }

    @HandleBeforeSave
    public void beforeSave(Route e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeSave] " + e);
    }

    @HandleBeforeDelete
    public void beforeDelete(Route e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeDelete] " + e);
    }

    @HandleAfterCreate
    public void afterCreate(Route e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterCreate] " + e);
    }

    @HandleAfterSave
    public void afterSave(Route e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterSave] " + e);
    }

    @HandleAfterDelete
    public void afterDelete(Route e) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에서는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleafterDelete] " + e);
    }

}
