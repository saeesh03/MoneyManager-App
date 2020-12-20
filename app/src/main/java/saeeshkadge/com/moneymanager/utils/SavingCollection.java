package saeeshkadge.com.moneymanager.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import saeeshkadge.com.moneymanager.model.Saving;

public class SavingCollection {
    private List<Saving> savings;

    public SavingCollection(List<Saving> savings) {
        this.savings = savings;
    }

    public Long getTotalSaving(){
        Long totalSaving = 0l;
        for (Saving saving : savings) {
            totalSaving += saving.getAmount();
        }

        return totalSaving;
    }

    public Map<String, List<Saving>> groupByDate() {
        Map<String, List<Saving>> savingsByDate = new HashMap<>();
        for (Saving saving : savings) {
            if(savingsByDate.get(saving.getDate()) == null){
                List<Saving> savingsList = new ArrayList<>();
                savingsList.add(saving);
                savingsByDate.put(saving.getDate(), savingsList);

            } else {
                savingsByDate.get(saving.getDate()).add(saving);
            }
        }

        return savingsByDate;
    }

    public List<Saving> withoutMoneyTransfer() {
        ArrayList<Saving> savings = new ArrayList<>();
        for (Saving saving : this.savings) {
            //if(!Objects.equals(saving.getType(), "Money-Transfer"))
                savings.add(saving);
        }

        return savings;
    }
}
