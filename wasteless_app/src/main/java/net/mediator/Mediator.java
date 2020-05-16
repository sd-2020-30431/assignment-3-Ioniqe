package net.mediator;

import net.mediator.command.*;
import net.mediator.handler.*;
import net.mediator.interfaces.Handler;
import net.mediator.interfaces.Request;
import net.mediator.interfaces.Response;
import net.mediator.query.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Mediator implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Map<Class<? extends Request>, Class<? extends Handler<? extends Request, ? extends Response>>> handlerMapper;

    public Mediator(){
        this.handlerMapper = new HashMap<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        handlerMapper.put(CharityQuery.class, ReadCharitiesQueryHandler.class);
        handlerMapper.put(ItemQuery.class, ReadItemQueryHandler.class);
        handlerMapper.put(ItemsQuery.class, ReadItemsQueryHandler.class);
        handlerMapper.put(ListQuery.class, ReadListsQueryHandler.class);
        handlerMapper.put(ReportQuery.class, ReadReportQueryHandler.class);
        handlerMapper.put(FindUserQuery.class, FindUserQueryHandler.class);
        handlerMapper.put(GoalQuery.class, ReadGoalQueryHandler.class);
        handlerMapper.put(DeleteItemCommand.class, DeleteItemCommandHandler.class);
        handlerMapper.put(DeleteListCommand.class, DeleteListCommandHandler.class);
        handlerMapper.put(SaveItemCommand.class, SaveItemCommandHandler.class);
        handlerMapper.put(SaveListCommand.class, SaveListCommandHandler.class);
        handlerMapper.put(SaveUserCommand.class, SaveUserCommandHandler.class);
    }

    public <T extends Request, R extends Response> Handler<T, R> handle(T request){
        Class<? extends Handler<? extends Request, ? extends Response>> handlerType = handlerMapper.get(request.getClass());
        return (Handler<T, R>) applicationContext.getBean(handlerType);
    }

}
