package pl.com.bottega.cms.model.commands;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by freszczypior on 2017-12-22.
 */
@Component
@Getter
public class ValidationErrors {

    private Map<String, String> errors = new HashMap<>();

    public ValidationErrors() {
    }

    public ValidationErrors(String field, String error) {
        errors.put(field, error);
    }

    public void add(String field, String error) {
        errors.put(field, error);
    }

    public boolean any() {
        return !errors.isEmpty();
    }
    public String getMessage(){
        return "Invalid input parameters";
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
