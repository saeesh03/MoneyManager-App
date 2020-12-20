package saeeshkadge.com.moneymanager.presenter;

import saeeshkadge.com.moneymanager.database.ExpenseDatabaseHelper;
import saeeshkadge.com.moneymanager.model.Expense;
import saeeshkadge.com.moneymanager.view.ExpenseView;

import static saeeshkadge.com.moneymanager.utils.DateUtil.getCurrentDate;

public class ExpensePresenter {

  private ExpenseDatabaseHelper database;
  private ExpenseView view;

  public ExpensePresenter(ExpenseDatabaseHelper expenseDatabaseHelper, ExpenseView view) {
    this.database = expenseDatabaseHelper;
    this.view = view;
  }

  public boolean addExpense() {
    String amount = view.getAmount();

    if(amount.isEmpty()) {
      view.displayError();
      return false;
    }

    Expense expense = new Expense(Long.valueOf(amount), view.getType(), getCurrentDate());
    database.addExpense(expense);
    return true;
  }

  public void setExpenseTypes() {
    view.renderExpenseTypes(database.getExpenseTypes());
  }
}
