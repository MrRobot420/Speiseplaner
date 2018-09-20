package com.mobileapplikationenfhws.speiseplan;

public class Mensa
{

    public Mensa() {

    }

    public Mensa(String id, String name, MealUrl mealUrl, Self self) {
        this.id = id;
        this.name = name;
        this.mealUrl = mealUrl;
        this.self = self;
    }

    private String id;

    private String name;

    private MealUrl mealUrl;

    private Self self;


    // ID  getter & setter
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }


    // NAME  getter & setter
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }



    // MEAL_URL  getter & setter
    public MealUrl getMealUrl ()
    {
        return mealUrl;
    }

    public void setMealUrl (MealUrl mealUrl)
    {
        this.mealUrl = mealUrl;
    }




    // SELF  getter & setter
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
        return "ClassPojo [id = "+id+", name = "+name+", self = "+self+", mealUrl = "+mealUrl+"]";
    }
}
