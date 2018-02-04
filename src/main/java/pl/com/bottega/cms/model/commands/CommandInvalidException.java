package pl.com.bottega.cms.model.commands;

/**
 * Created by freszczypior on 2017-12-22.
 */
public class CommandInvalidException extends RuntimeException {


    private ValidationErrors errors;

    public CommandInvalidException(ValidationErrors errors) {
        this.errors = errors;
    }

    public ValidationErrors getErrors() {
        return errors;
    }
}
