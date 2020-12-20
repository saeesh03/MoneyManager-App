package saeeshkadge.com.moneymanager.model;

public class Saving {
    //private String type;
    private String date;
    private Long amount;
    private Integer id;

    public Saving(Long amount, String date) {
        //this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public Saving(Integer id, Long amount, String date) {
        this(amount, date);
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    //public String getType() { return type;}

    public String getDate() { return date; }

    public Integer getId() { return id; }
}
