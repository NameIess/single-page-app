package com.training.epam.entity.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SearchingCriteria implements JsonDto {

    @Pattern(regexp = "^(?![= ]).{1,255}", message = "The string must be between 1 and 255 characters. The first character must not be '=' or ' '")
    private String currentName;

    public SearchingCriteria() {
    }

    @Override
    public String getId() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchingCriteria)) return false;

        SearchingCriteria that = (SearchingCriteria) o;

        return currentName != null ? currentName.equals(that.currentName) : that.currentName == null;
    }

    @Override
    public int hashCode() {
        return currentName != null ? currentName.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchingCriteria{");
        sb.append("currentName='").append(currentName).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
