package com.training.epam.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Composite implements Serializable {
    private String name;
    private int childrenAmount;
    private List<Composite> componentList = new ArrayList<>();
    private Iterator<Composite> iterator = null;


    public void addComponent(Composite component) {
        componentList.add(component);
        childrenAmount++;
    }

    public void removeComponent(Composite component) {
        componentList.remove(component);
    }

    public Iterator<Composite> createIterator() {
//        if (iterator == null) {
//            iterator = new CompositeIterator(componentList.iterator());
//        } else {
//            System.out.println("Iterator is already exist");
//        }

       return new CompositeIterator(componentList.iterator());
//        return iterator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildrenAmount() {
        return childrenAmount;
    }

    public void setChildrenAmount(int childrenAmount) {
        this.childrenAmount = childrenAmount;
    }

    public List<Composite> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Composite> componentList) {
        this.componentList = componentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composite)) return false;

        Composite composite = (Composite) o;

        if (getChildrenAmount() != composite.getChildrenAmount()) return false;
        if (getName() != null ? !getName().equals(composite.getName()) : composite.getName() != null) return false;
        return getComponentList() != null ? getComponentList().equals(composite.getComponentList()) : composite.getComponentList() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getChildrenAmount();
        result = 31 * result + (getComponentList() != null ? getComponentList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Composite{");
        sb.append("name='").append(name).append('\'');
        sb.append(", childrenAmount=").append(childrenAmount);
        sb.append(", componentList=").append(componentList);
        sb.append('}');
        return sb.toString();
    }


}
