package pl.com.bottega.cms.app;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.com.bottega.cms.app.handlers.Handler;
import pl.com.bottega.cms.model.commands.Command;
import pl.com.bottega.cms.model.commands.CommandInvalidException;
import pl.com.bottega.cms.model.commands.ValidateErrors;

import java.util.Map;
import java.util.Optional;

/**
 * Created by freszczypior on 2017-12-22.
 */
@Component
public class CommandGateway {

    private ApplicationContext applicationContext;

    public CommandGateway(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public void execute(Command command) {
        validate(command);
        Handler handler = handlerFor(command);
        handler.handle(command);
    }

    private void validate(Command command) {
        ValidateErrors errors = new ValidateErrors();
        command.validate(errors);
        if (errors.any()) {
            throw new CommandInvalidException(errors);
        }
    }

    private Handler handlerFor(Command command) {
        Map<String, Handler> handlers = applicationContext.getBeansOfType(Handler.class);
        Optional<Handler> handler =
                handlers.values().stream().filter((h) -> h.canHandle(command)).findFirst();
        return handler.orElseThrow(() ->
                new IllegalArgumentException("No handler found for " + command.getClass()));
    }
}
