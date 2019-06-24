package cp317.wlu.ca.fridgepal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {


    public ConfirmFragment() {
        // Required empty public constructor
    }

    interface onNextPressedListener {
        void onNextPressed(View view);
    }

    private onNextPressedListener onNextPressedListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);


        Button nextButton = view.findViewById(R.id.button4);
        nextButton.setOnClickListener(onNextPressedListener::onNextPressed);
        return view;
    }

    public void setOnNextPressedListener(onNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }
}
