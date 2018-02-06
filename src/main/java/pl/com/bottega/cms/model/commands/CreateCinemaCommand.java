package pl.com.bottega.cms.model.commands;

/**
 * Created by freszczypior on 2017-12-22.
 */

public class CreateCinemaCommand implements Command {

    private String name;
    private String NAME_CITY_PATTERN = "[a-zA-Z]*";
    private String city;

    public void validate(ValidationErrors errors){
        validatePresence(errors, "name", name);
        validatePresence(errors, "city", city);
        if (name != null && city != null){
            if (!name.matches(NAME_CITY_PATTERN))
                errors.add("name", "Cinema name should only contains letters between a-z and A-Z");
            if (!city.matches(NAME_CITY_PATTERN))
                errors.add("city", "City name should only contains letters between  a-z and A-Z");
        }
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
