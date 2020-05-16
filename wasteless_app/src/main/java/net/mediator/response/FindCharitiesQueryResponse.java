package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Charity;

import java.util.List;

public class FindCharitiesQueryResponse implements Response {
    private List<Charity> charities;

    public FindCharitiesQueryResponse(List<Charity> charities) {
        this.charities = charities;
    }

    public List<Charity> getCharities() {
        return charities;
    }

    public void setCharities(List<Charity> charities) {
        this.charities = charities;
    }
}
