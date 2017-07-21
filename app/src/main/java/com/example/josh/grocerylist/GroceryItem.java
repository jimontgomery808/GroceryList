package com.example.josh.grocerylist;


public class GroceryItem
{
    private String name;
    String unitOfMeasure;
    private double cost;
    private double quanity;

    public String getUnitOfMeasure()
    {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure)
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getQuanity()
    {
        return quanity;
    }

    public void setQuanity(double quanity)
    {
        this.quanity = quanity;
    }

    public GroceryItem(String setName, double setCost, double setQuantity, String setUnit)
    {
        name = setName;
        cost = setCost;
        quanity = setQuantity;
        unitOfMeasure = setUnit;
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
