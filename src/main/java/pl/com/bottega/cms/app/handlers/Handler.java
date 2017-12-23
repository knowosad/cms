package pl.com.bottega.cms.app.handlers;

import pl.com.bottega.cms.model.commands.Command;

/**
 * Created by freszczypior on 2017-12-22.
 */
public interface Handler<C extends Command> {

    void handle(C command);

    Class<? extends Command> getSupportedCommandClass();

    default boolean canHandle(Command command) {
        return command.getClass().equals(getSupportedCommandClass());
    }

}
