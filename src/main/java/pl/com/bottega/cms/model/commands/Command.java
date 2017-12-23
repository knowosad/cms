package pl.com.bottega.cms.model.commands;

/**
 * Created by freszczypior on 2017-12-22.
 */
public interface Command {

    default void validate(ValidateErrors errors){

    }

    default void validatePresence(ValidateErrors errors, String field, String value){
        if (value == null || value.trim().length() == 0){
            errors.add(field, "can't be blank");
        }
    }

}
