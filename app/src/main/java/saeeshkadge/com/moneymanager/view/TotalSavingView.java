package saeeshkadge.com.moneymanager.view;

import java.util.List;
import java.util.Map;

import saeeshkadge.com.moneymanager.model.Saving;

public interface TotalSavingView {
    void displayCurrentWeeksSaving(Map<String, List<Saving>> savingsByDate);


    void displayTotalSaving(Long totalExpense);
}
