package saeeshkadge.com.moneymanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import saeeshkadge.com.moneymanager.activity.SavingFragment;
import saeeshkadge.com.moneymanager.activity.ExpenseFragment;
import saeeshkadge.com.moneymanager.activity.TodaysExpenseFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
  public HomeViewPagerAdapter(FragmentManager supportFragmentManager) {
    super(supportFragmentManager);
  }

  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 0:
        return new ExpenseFragment();
      case 1:
        return new TodaysExpenseFragment();
      case 2:
        return new SavingFragment();
    }

    return null;
  }

  @Override
  public int getCount() {
    return 3;
  }
}
