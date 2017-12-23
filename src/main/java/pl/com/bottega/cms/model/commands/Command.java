package pl.com.bottega.cms.model.commands;

import java.util.List;

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
    default void validatePresence(ValidateErrors errors, String field, List<String> list){
        if (list == null || list.isEmpty()){
            errors.add(field, "list can't be empty");
        }
    }

    default void validatePresence(ValidateErrors errors, String field, Integer value){
        if (value == null){
            errors.add(field, "can't be blank");
        }
    }

}
