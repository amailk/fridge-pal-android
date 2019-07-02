package cp317.wlu.ca.fridgepal.fridge;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cp317.wlu.ca.fridgepal.R;

public class FridgeFragment extends Fragment {

    private FridgeViewModel mViewModel;

    public static FridgeFragment newInstance() {
        return new FridgeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fridge_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FridgeViewModel.class);
        // TODO: Use the ViewModel
    }

}
