package cp317.wlu.ca.fridgepal.grocerylist;
import android.os.Bundle;

public class GroceryListActicity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_checkbox);

        final ListView listViewWithCheckbox = (ListView)findViewById(R.id.list_view_with_checkbox);
        final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();
    }
}
