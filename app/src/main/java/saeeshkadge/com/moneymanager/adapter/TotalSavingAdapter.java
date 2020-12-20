package saeeshkadge.com.moneymanager.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import saeeshkadge.com.moneymanager.R;
import saeeshkadge.com.moneymanager.model.Saving;
import saeeshkadge.com.moneymanager.utils.SavingCollection;

import static saeeshkadge.com.moneymanager.utils.DateUtil.getDayName;

public class TotalSavingAdapter implements ExpandableListAdapter {
    private Context context;
    private final Map<String, List<Saving>> savings;

    public TotalSavingAdapter(Context context, Map<String, List<Saving>> savings) {
        this.context = context;
        this.savings = savings;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return savings.keySet().size();
    }

    @Override
    public int getChildrenCount(int position) {
        return savings.get(savings.keySet().toArray()[position]).size();
    }

    @Override
    public Object getGroup(int position) {
        String date = (String) this.savings.keySet().toArray()[position];
        Long totalSaving = new SavingCollection(this.savings.get(date)).getTotalSaving();

        return date + " (" + getDayName(date) + ") - " + context.getString(R.string.rupee_sym) + totalSaving;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    /*@Override
    public Object getChild(int parent, int child) {
        Saving saving = savings.get(savings.keySet().toArray()[parent]).get(child);
        //return saving.getType() + " - " + saving.getAmount();
        }*/

    @Override
    public long getGroupId(int position) {
        return position;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View converterView, ViewGroup viewGroup) {
        String parentText = (String) getGroup(parent);

        if(converterView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converterView = inflater.inflate(R.layout.expense_header_text_box, viewGroup, false);
        }

        TextView textBox = (TextView) converterView.findViewById(R.id.expense_header_text_box);
        textBox.setText(parentText);

        return converterView;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View converterView, ViewGroup viewGroup) {
        String childText = (String) getChild(parent, child);

        if(converterView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converterView = inflater.inflate(R.layout.expense_text_box, viewGroup, false);
        }

        TextView textBox = (TextView) converterView.findViewById(R.id.expense_text_box);
        textBox.setText(childText);

        return converterView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
