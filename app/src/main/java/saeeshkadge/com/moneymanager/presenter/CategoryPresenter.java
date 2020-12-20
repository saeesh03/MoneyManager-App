package saeeshkadge.com.moneymanager.presenter;

import saeeshkadge.com.moneymanager.database.ExpenseDatabaseHelper;
import saeeshkadge.com.moneymanager.model.ExpenseType;
import saeeshkadge.com.moneymanager.view.AddCategoryView;

public class CategoryPresenter {
  private final AddCategoryView view;
  private final ExpenseDatabaseHelper database;

  public CategoryPresenter(AddCategoryView view, ExpenseDatabaseHelper database) {
    this.view = view;
    this.database = database;
  }

  public boolean addCategory() {
    String newCategory = view.getCategory();
    if(newCategory.isEmpty()){
      view.displayError();
      return false;
    }

    database.addExpenseType(new ExpenseType(newCategory));
    return true;
  }
}
