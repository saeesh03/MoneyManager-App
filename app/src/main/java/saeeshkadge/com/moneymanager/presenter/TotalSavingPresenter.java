package saeeshkadge.com.moneymanager.presenter;

import saeeshkadge.com.moneymanager.database.SavingDatabaseHelper;
import saeeshkadge.com.moneymanager.utils.SavingCollection;
import saeeshkadge.com.moneymanager.view.TotalSavingView;

public class TotalSavingPresenter {

    private TotalSavingView view;
    private SavingDatabaseHelper database;
    private SavingCollection savingCollection;

    public TotalSavingPresenter(SavingDatabaseHelper database, TotalSavingView view) {
        this.database = database;
        this.view = view;
        savingCollection = new SavingCollection(this.database.getCurrentWeeksSaving());
    }

    public void renderTotalSaving() {
        view.displayTotalSaving(savingCollection.getTotalSaving());
    }

    public void renderCurrentWeeksSaving() {
        view.displayCurrentWeeksSaving(savingCollection.groupByDate());
    }
}
