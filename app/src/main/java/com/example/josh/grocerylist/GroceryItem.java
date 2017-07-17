package com.example.josh.grocerylist;


public class GroceryItem
{
    private String name;
    private double cost;
    private int quanity;


    public int getQuanity()
    {
        return quanity;
    }

    public void setQuanity(int quanity)
    {
        this.quanity = quanity;
    }

    public GroceryItem(String setName, double setCost, int setQuantity)
    {
        name = setName;
        cost = setCost;
        quanity = setQuantity;
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
