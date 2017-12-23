package pl.com.bottega.cms.model.commands;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by freszczypior on 2017-12-22.
 */
@Component
public class ValidateErrors {

    private Map<String, String> errors = new HashMap<>();

    public void add(String field, String error) {
        errors.put(field, error);
    }

    public boolean any() {
        return !errors.isEmpty();
    }
}
