package models;

import java.util.List;

public class DeserializeJson {
    private List<CityInfo> results;
    private City current;

    public List<CityInfo> getResults() {
        return results;
    }

    public City getCurrent() {
        return current;
    }
}
