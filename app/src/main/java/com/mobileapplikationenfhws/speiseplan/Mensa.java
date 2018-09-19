package com.mobileapplikationenfhws.speiseplan;

public class Mensa
{
    private String id;

    private String name;

    private Self self;

    private MealUrl mealUrl;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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

    public MealUrl getMealUrl ()
    {
        return mealUrl;
    }

    public void setMealUrl (MealUrl mealUrl)
    {
        this.mealUrl = mealUrl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", self = "+self+", mealUrl = "+mealUrl+"]";
    }
}
