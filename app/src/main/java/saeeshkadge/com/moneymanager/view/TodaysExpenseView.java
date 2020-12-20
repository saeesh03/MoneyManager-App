package saeeshkadge.com.moneymanager.view;

import java.util.List;

import saeeshkadge.com.moneymanager.model.Expense;

public interface TodaysExpenseView {
  void displayTotalExpense(Long totalExpense);
  void displayTodaysExpenses(List<Expense> expenses);
}
