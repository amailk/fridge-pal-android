package cp317.wlu.ca.fridgepal;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class gd_fragment extends Fragment {

    private GDay interfaceImplementor;

    public interface GDay {
        public void gdaysearch(String day);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.interfaceImplementor = (GDay)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gd_fragment, container, false);
    }

}
