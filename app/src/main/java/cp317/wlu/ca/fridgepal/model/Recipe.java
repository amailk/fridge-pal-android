package cp317.wlu.ca.fridgepal.model;

import java.util.List;
import java.util.Objects;

public class Recipe {
    boolean vegetarian;
    String id;
    String title;
    int readyInMinutes;
    String image;
    String instructions;
//    List<Instruction> analyzedInstructions;

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
//
//    public List<Instruction> getAnalyzedInstructions() {
//        return analyzedInstructions;
//    }
//
//    public void setAnalyzedInstructions(List<Instruction> analyzedInstructions) {
//        this.analyzedInstructions = analyzedInstructions;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return title.equals(recipe.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}