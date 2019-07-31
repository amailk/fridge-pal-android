package cp317.wlu.ca.fridgepal.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Instruction;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder> {
    private List<Instruction.Step> steps;

    public InstructionsAdapter(List<Instruction.Step> steps) {
        System.out.println("Steps size: " + steps.size());
        this.steps = steps;
    }

    @Override
    public InstructionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View ingredientView = inflater.inflate(R.layout.item_instruction, parent, false);

        ViewHolder viewHolder = new ViewHolder(ingredientView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InstructionsAdapter.ViewHolder viewHolder, int position) {
        Instruction.Step step = steps.get(position);

        viewHolder.number.setText(Integer.toString(step.getNumber()));
        viewHolder.step.setText(step.getStep());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView step;

        public ViewHolder(View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            step = itemView.findViewById(R.id.step);
        }
    }

}
