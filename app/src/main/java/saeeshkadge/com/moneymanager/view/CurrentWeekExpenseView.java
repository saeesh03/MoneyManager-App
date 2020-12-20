package saeeshkadge.com.moneymanager.view;

import java.util.List;
import java.util.Map;

import saeeshkadge.com.moneymanager.model.Expense;

public interface CurrentWeekExpenseView {
  void displayCurrentWeeksExpenses(Map<String, List<Expense>> expensesByDate);

  void displayTotalExpenses(Long totalExpense);
}
