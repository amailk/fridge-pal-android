package cp317.wlu.ca.fridgepal.model;

import java.util.Date;
import java.util.UUID;

public class Food
{
    private UUID foodId;
    private String foodName;
    private Date dateAdded;
    private Date expiryDate;
    private Boolean isPriority;
    private Boolean isFavorite;
    private String foodCategory;

    // the constructor should have String param category
    public Food(String name)
    {
        foodId = UUID.randomUUID();
        foodName = name;
        dateAdded = new Date();
        isFavorite = false;
        //foodCategory = category;
    }

    public UUID getFoodId()
    {
        return foodId;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public void setExpiryDate(Date date)
    {
        expiryDate = date;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setIsFavorite(Boolean favorite)
    {
        isFavorite = favorite;
    }

    public Boolean getIsFavorite()
    {
        return isFavorite;
    }

    public void setFoodCategory(String category)
    {
        foodCategory = category;
    }

    public String getFoodCategory()
    {
        return foodCategory;
    }

    private void setIsPriority(Date date)
    {
        long diff = expiryDate.getTime() - date.getTime();
    }

    public Boolean getIsPriority()
    {
        // perform calculation...
        return isPriority;
    }
}