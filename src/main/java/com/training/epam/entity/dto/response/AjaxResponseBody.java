package com.training.epam.entity.dto.response;

import com.training.epam.entity.Composite;

import java.util.List;

public class AjaxResponseBody {

    private String message;
    private List<Composite> compositeList;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Composite> getCompositeList() {
        return compositeList;
    }

    public void setCompositeList(List<Composite> compositeList) {
        this.compositeList = compositeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AjaxResponseBody)) return false;

        AjaxResponseBody that = (AjaxResponseBody) o;

        if (getMessage() != null ? !getMessage().equals(that.getMessage()) : that.getMessage() != null) return false;
        return getCompositeList() != null ? getCompositeList().equals(that.getCompositeList()) : that.getCompositeList() == null;
    }

    @Override
    public int hashCode() {
        int result = getMessage() != null ? getMessage().hashCode() : 0;
        result = 31 * result + (getCompositeList() != null ? getCompositeList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AjaxResponseBody{");
        sb.append("message='").append(message).append('\'');
        sb.append(", compositeList=").append(compositeList);
        sb.append('}');
        return sb.toString();
    }
}
