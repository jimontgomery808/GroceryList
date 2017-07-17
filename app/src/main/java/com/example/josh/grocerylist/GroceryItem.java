package com.example.josh.grocerylist;


public class GroceryItem
{
    private String name;
    private double cost;

    public GroceryItem(String setName, double setCost)
    {
        name = setName;
        cost = setCost;
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
        return(name + "       " + Double.toString(cost));
    }
}
