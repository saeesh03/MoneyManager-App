package saeeshkadge.com.moneymanager.presenter;

import saeeshkadge.com.moneymanager.database.SavingDatabaseHelper;
import saeeshkadge.com.moneymanager.model.Saving;
import saeeshkadge.com.moneymanager.view.SavingView;

import static saeeshkadge.com.moneymanager.utils.DateUtil.getCurrentDate;

public class SavingsPresenter {

    private SavingDatabaseHelper database;
    private SavingView view;

    public SavingsPresenter(SavingDatabaseHelper savingDatabaseHelper, SavingView view) {
        this.database = savingDatabaseHelper;
        this.view = view;
    }

    public boolean addSaving() {
        String amount = view.getAmount();

        if(amount.isEmpty()) {
            view.displayError();
            return false;
        }

        Saving saving = new Saving(Long.valueOf(amount), getCurrentDate());
        database.addSaving(saving);
        return true;
    }
    //public void setExpenseTypes() { view.renderExpenseTypes(database.getExpenseTypes()); }

}
