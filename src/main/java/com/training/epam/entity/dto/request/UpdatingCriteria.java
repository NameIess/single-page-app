package com.training.epam.entity.dto.request;

import javax.validation.constraints.Pattern;

public class UpdatingCriteria implements JsonDto {

    @Pattern(regexp = "^(?![= ]).{1,255}", message = "The string must be between 1 and 255 characters. The first character must not be '=' or ' '")
    private String currentName;

    @Pattern(regexp = "^(?![= ]).{1,255}", message = "The string must be between 1 and 255 characters. The first character must not be '=' or ' '")
    private String updatedName;

    public UpdatingCriteria() {
    }

    @Override
    public String getId() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatingCriteria)) return false;

        UpdatingCriteria that = (UpdatingCriteria) o;

        if (currentName != null ? !currentName.equals(that.currentName) : that.currentName != null) return false;
        return getUpdatedName() != null ? getUpdatedName().equals(that.getUpdatedName()) : that.getUpdatedName() == null;
    }

    @Override
    public int hashCode() {
        int result = currentName != null ? currentName.hashCode() : 0;
        result = 31 * result + (getUpdatedName() != null ? getUpdatedName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateCriteria{");
        sb.append("currentName='").append(currentName).append('\'');
        sb.append(", updatedName='").append(updatedName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
