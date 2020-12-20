package saeeshkadge.com.moneymanager.presenter;

import saeeshkadge.com.moneymanager.activity.CurrentMonthExpenseFragment;
import saeeshkadge.com.moneymanager.activity.CurrentWeekExpenseFragment;
import saeeshkadge.com.moneymanager.activity.TotalSavingFragment;
import saeeshkadge.com.moneymanager.view.NavigationDrawerItemView;

public class NavigationDrawerPresenter {

  public static final String THIS_WEEKS_EXPENSE = "This Week Expense";
  public static final String THIS_MONTHS_EXPENSE = "This Month Expense";
  public static final String HOME = "Home";
  public static final String SAVINGS = "Total Savings";
  private NavigationDrawerItemView view;

  public NavigationDrawerPresenter(NavigationDrawerItemView view) {
    this.view = view;
  }

  public void onItemSelected(String drawerItem) {
    switch (drawerItem){
      case SAVINGS:
        view.render(new TotalSavingFragment());
        break;
      case THIS_WEEKS_EXPENSE:
        view.render(new CurrentWeekExpenseFragment());
        break;
      case THIS_MONTHS_EXPENSE:
        view.render(new CurrentMonthExpenseFragment());
        break;
      case HOME:
        view.goToHome();
        break;
    }
  }
}
