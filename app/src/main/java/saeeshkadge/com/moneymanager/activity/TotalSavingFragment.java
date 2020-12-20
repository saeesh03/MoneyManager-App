package saeeshkadge.com.moneymanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import saeeshkadge.com.moneymanager.R;
import saeeshkadge.com.moneymanager.adapter.TotalSavingAdapter;
import saeeshkadge.com.moneymanager.database.SavingDatabaseHelper;
import saeeshkadge.com.moneymanager.model.Saving;
import saeeshkadge.com.moneymanager.presenter.TotalSavingPresenter;
import saeeshkadge.com.moneymanager.view.TotalSavingView;

public class TotalSavingFragment extends Fragment implements TotalSavingView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.total_saving, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SavingDatabaseHelper savingDatabaseHelper = new SavingDatabaseHelper(getActivity());
        TotalSavingPresenter presenter = new TotalSavingPresenter(savingDatabaseHelper, this);
        presenter.renderTotalSaving();
        presenter.renderCurrentWeeksSaving();
        savingDatabaseHelper.close();
    }

    @Override
    public void displayCurrentWeeksSaving(Map<String, List<Saving>> savingsByDate) {
        ExpandableListView listView = (ExpandableListView) getActivity().findViewById(R.id.total_saving_list);
        listView.setAdapter(new TotalSavingAdapter(getActivity(), savingsByDate));
    }

    @Override
    public void displayTotalSaving(Long totalSaving) {
        TextView totalSavingTextBox = (TextView) getActivity().findViewById(R.id.total_saving);
        totalSavingTextBox.setText(getString(R.string.total_saving) + " " + getString(R.string.rupee_sym) + totalSaving);
    }
}
