package pl.com.bottega.cms.model.commands;

/**
 * Created by freszczypior on 2017-12-22.
 */
public class CommandInvalidException extends RuntimeException {


    private ValidateErrors errors;

    public CommandInvalidException(ValidateErrors errors) {
        this.errors = errors;
    }

    public ValidateErrors getErrors() {
        return errors;
    }
}
