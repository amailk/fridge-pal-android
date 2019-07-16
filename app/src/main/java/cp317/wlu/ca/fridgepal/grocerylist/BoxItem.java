package cp317.wlu.ca.fridgepal.grocerylist;

public class BoxItem {
    private boolean checked = false;

    private String itemText = "";

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}
/Users/ellenshang/StudioProjects/fridge-pal-android/app/src/main/java/cp317/wlu/ca/fridgepal/grocerylist