package cp317.wlu.ca.fridgepal.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@IgnoreExtraProperties
public class Food implements Serializable {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM YYYY");

    private String name;
    private String addedDate;
    private String expiryDate;
    private String category;
    @Exclude
    private String Uuid;
    @Exclude
    private String image;

    public Food() {
        // Default constructor required for Firebase
    }

    public Food(String name, String category, Date expDate) {
        this.addedDate = new Date().toString();
        this.name = name;
        this.category = category;
        this.expiryDate = DATE_FORMAT.format(expDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Exclude
    public LocalDate getExpiryDateAsDate() {
        return LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("dd LLL yyyy"));
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Exclude
    public String getUuid() {
        return Uuid;
    }

    @Exclude
    public String getImage() {
        return image;
    }

    @Exclude
    public void setImage(String image) {
        this.image = image;
    }

    @Exclude
    public void setUuid(String uuid) {
        Uuid = uuid;
    }
}