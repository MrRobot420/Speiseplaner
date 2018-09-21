package com.mobileapplikationen.speiseplan.meallist;

import android.support.annotation.NonNull;

public class Meal implements Comparable<Meal>
{

    public Meal() {

    }

    public Meal(String uid, String id, String name, String price, String pricebed, String priceguest, String foodtype, String validOnDate, String additivenumbers, Self self) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.price = price;
        this.pricebed = pricebed;
        this.priceguest = priceguest;
        this.foodtype = foodtype;
        this.validOnDate = validOnDate;
        this.additivenumbers = additivenumbers;
        this.self = self;
    }

    private String uid;

    private String id;

    private String name;

    private String price;

    private String pricebed;

    private String priceguest;

    private String foodtype;

    private String validOnDate;

    private String additivenumbers;

    private Self self;



    // UID  getter & setter
    public String getUid ()
    {
        return uid;
    }

    public void setUid (String uid)
    {
        this.uid = uid;
    }



    // ID  getter & setter
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }



    // NAME getter & setter
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }



    // PRICE  getter & setter
    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }



    // PRICE_BED getter & setter
    public String getPricebed ()
    {
        return pricebed;
    }

    public void setPricebed (String pricebed)
    {
        this.pricebed = pricebed;
    }



    // PRICE_GUEST getter & setter
    public String getPriceguest ()
    {
        return priceguest;
    }

    public void setPriceguest (String priceguest)
    {
        this.priceguest = priceguest;
    }



    // FOOD_TYPE getter & setter
    public String getFoodtype ()
    {
        return foodtype;
    }

    public void setFoodtype (String foodtype)
    {
        this.foodtype = foodtype;
    }




    // VALID_ON_DATE getter & setter
    public String getValidOnDate ()
    {
        return validOnDate;
    }

    public void setValidOnDate (String validOnDate)
    {
        this.validOnDate = validOnDate;
    }



    // ADDITIVE_NUMBERS getter & setter
    public String getAdditivenumbers ()
    {
        return additivenumbers;
    }

    public void setAdditivenumbers (String additivenumbers)
    {
        this.additivenumbers = additivenumbers;
    }



    // SELF getter & setter
    public Self getSelf ()
    {
        return self;
    }

    public void setSelf (Self self)
    {
        this.self = self;
    }



    @Override
    public String toString()
    {
        return "Meal [uid = "+uid+", pricebed = "+pricebed+", id = "+id+", price = "+price+", priceguest = "+priceguest+", name = "+name+", self = "+self+", validOnDate = "+validOnDate+", additivenumbers = "+additivenumbers+", foodtype = "+foodtype+"]";
    }

    @Override
    public int compareTo(@NonNull Meal meal) {
        return this.foodtype.toLowerCase().compareTo(meal.foodtype.toLowerCase());
    }
}