package pl.com.bottega.cms.model.commands;

/**
 * Created by freszczypior on 2017-12-22.
 */

public class CreateCinemaCommand implements Command {

    private String name;

    private String city;

    public void validate(ValidationErrors errors){
        validatePresence(errors, "name", name);
        validatePresence(errors, "city", city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
