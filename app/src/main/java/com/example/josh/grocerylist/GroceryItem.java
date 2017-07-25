package com.example.josh.grocerylist;


import android.os.Parcel;
import android.os.Parcelable;

public class GroceryItem implements Parcelable
{
    private String name;
    String unitOfMeasure;
    private double cost;
    private double quanity;

    protected GroceryItem(Parcel in)
    {
        name = in.readString();
        unitOfMeasure = in.readString();
        cost = in.readDouble();
        quanity = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(unitOfMeasure);
        dest.writeDouble(cost);
        dest.writeDouble(quanity);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>()
    {
        @Override
        public GroceryItem createFromParcel(Parcel in)
        {
            return new GroceryItem(in);
        }

        @Override
        public GroceryItem[] newArray(int size)
        {
            return new GroceryItem[size];
        }
    };

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
