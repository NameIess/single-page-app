package com.training.epam.entity.dto.request;

import javax.validation.constraints.Pattern;

public class CreatingCriteria implements JsonDto {

    @Pattern(regexp = "^(?![= ]).{1,255}", message = "The string must be between 1 and 255 characters. The first character must not be '=' or ' '")
    private String parentName;

    @Pattern(regexp = "^(?![= ]).{1,255}", message = "The string must be between 1 and 255 characters. The first character must not be '=' or ' '")
    private String childName;

    public CreatingCriteria() {
    }

    @Override
    public String getId() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatingCriteria)) return false;

        CreatingCriteria that = (CreatingCriteria) o;

        if (parentName != null ? !parentName.equals(that.parentName) : that.parentName != null) return false;
        return getChildName() != null ? getChildName().equals(that.getChildName()) : that.getChildName() == null;
    }

    @Override
    public int hashCode() {
        int result = parentName != null ? parentName.hashCode() : 0;
        result = 31 * result + (getChildName() != null ? getChildName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreatingCriteria{");
        sb.append("parentName='").append(parentName).append('\'');
        sb.append(", childName='").append(childName).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
