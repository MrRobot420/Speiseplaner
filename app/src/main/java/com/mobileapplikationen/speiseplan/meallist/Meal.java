package com.mobileapplikationen.speiseplan.meallist;

public class Meal
{

    public Meal(String uid, String pricebed, String id, String price, String priceguest, String name, Self self, String validOnDate, String additivenumbers, String foodtype) {
        this.uid = uid;
        this.pricebed = pricebed;
        this.id = id;
        this.price = price;
        this.priceguest = priceguest;
        this.name = name;
        this.self = self;
        this.validOnDate = validOnDate;
        this.additivenumbers = additivenumbers;
        this.foodtype = foodtype;
    }

    private String uid;

    private String pricebed;

    private String id;

    private String price;

    private String priceguest;

    private String name;

    private Self self;

    private String validOnDate;

    private String additivenumbers;

    private String foodtype;

    public String getUid ()
    {
        return uid;
    }

    public void setUid (String uid)
    {
        this.uid = uid;
    }

    public String getPricebed ()
    {
        return pricebed;
    }

    public void setPricebed (String pricebed)
    {
        this.pricebed = pricebed;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getPriceguest ()
    {
        return priceguest;
    }

    public void setPriceguest (String priceguest)
    {
        this.priceguest = priceguest;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Self getSelf ()
    {
        return self;
    }

    public void setSelf (Self self)
    {
        this.self = self;
    }

    public String getValidOnDate ()
    {
        return validOnDate;
    }

    public void setValidOnDate (String validOnDate)
    {
        this.validOnDate = validOnDate;
    }

    public String getAdditivenumbers ()
    {
        return additivenumbers;
    }

    public void setAdditivenumbers (String additivenumbers)
    {
        this.additivenumbers = additivenumbers;
    }

    public String getFoodtype ()
    {
        return foodtype;
    }

    public void setFoodtype (String foodtype)
    {
        this.foodtype = foodtype;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [uid = "+uid+", pricebed = "+pricebed+", id = "+id+", price = "+price+", priceguest = "+priceguest+", name = "+name+", self = "+self+", validOnDate = "+validOnDate+", additivenumbers = "+additivenumbers+", foodtype = "+foodtype+"]";
    }
}