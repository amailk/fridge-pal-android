package cp317.wlu.ca.fridgepal.grocerylist;

import androidx.lifecycle.ViewModel;

public class GroceryListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private Box itemBox;

    private TextView itemTextView;

    public ListViewHolder(View itemView) {
        super(itemView);
    }

    public Box getItemBox() {
        return itemBox;
    }

    public void setItemCheckbox(CheckBox itemBox) {
        this.itemBox = itemBox;
    }

    public TextView getItemText() {
        return itemTextView;
    }

    public void addItemView(TextView itemText) {
        this.itemText = itemText;
    }

}
