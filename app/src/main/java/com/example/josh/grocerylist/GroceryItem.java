package com.example.josh.grocerylist;


public class GroceryItem
{
    private String name;
    private double cost;

    public GroceryItem(String setName, String setCost)
    {
        name = setName;
        cost = Double.parseDouble(setCost);
    }
    public String getName()
    {
        return name;
    }

    public double getCost()
    {
        return cost;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        return("Item: " + name + "     Price: " + cost);
    }
}
