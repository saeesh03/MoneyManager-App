package saeeshkadge.com.moneymanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import saeeshkadge.com.moneymanager.R;
import saeeshkadge.com.moneymanager.database.SavingDatabaseHelper;
import saeeshkadge.com.moneymanager.presenter.SavingsPresenter;
import saeeshkadge.com.moneymanager.view.SavingView;

public class SavingFragment extends Fragment implements SavingView, View.OnClickListener  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_savings, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SavingDatabaseHelper savingDatabaseHelper = new SavingDatabaseHelper(this.getActivity());
        SavingsPresenter savingPresenter = new SavingsPresenter(savingDatabaseHelper, this);
        //expensePresenter.setExpenseTypes();
        savingDatabaseHelper.close();

        Button addSavingButton = (Button) getActivity().findViewById(R.id.add_saving);
        addSavingButton.setOnClickListener(this);
    }

    @Override
    public String getAmount() {
        TextView view = (TextView) getActivity().findViewById(R.id.amount);
        return view.getText().toString();
    }

    /*@Override
    public String getType() {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.expense_type);
        return (String) spinner.getSelectedItem();
    }

    @Override
    public void renderExpenseTypes(List<String> expenseTypes) {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.expense_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, expenseTypes);
        spinner.setAdapter(adapter);
    }*/

    @Override
    public void displayError() {
        TextView view = (TextView) getActivity().findViewById(R.id.amount);
        view.setError(getActivity().getString(R.string.amount_empty_error));
    }

    @Override
    public void onClick(View view) {
        SavingDatabaseHelper savingDatabaseHelper = new SavingDatabaseHelper(this.getActivity());
        SavingsPresenter savingPresenter = new SavingsPresenter(savingDatabaseHelper, this);
        if(savingPresenter.addSaving()){
            MainActivity activity = (MainActivity) getActivity();
            Toast.makeText(activity, R.string.saving_add_successfully, Toast.LENGTH_LONG).show();
            activity.onSavingsAdded();
        }
        savingDatabaseHelper.close();
    }
}
