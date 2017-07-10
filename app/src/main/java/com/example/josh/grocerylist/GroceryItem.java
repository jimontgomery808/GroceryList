package com.example.josh.grocerylist;


public class GroceryItem
{
    private String name;
    private String cost;

    public GroceryItem(String setName, String setCost)
    {
        name = setName;
        cost = setCost;
    }
    public String getName()
    {
        return name;
    }

    public String getCost()
    {
        return cost;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        return("Item: " + name + "     Price: " + cost);
    }
}
