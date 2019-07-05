package cp317.wlu.ca.fridgepal.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cp317.wlu.ca.fridgepal.R;

public class FridgeFragment extends Fragment
{
    private Button viewContentsButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fridge_fragment_layout, container, false);

        viewContentsButton = (Button) v.findViewById(R.id.view_contents_button);
        viewContentsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), FridgeListActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}