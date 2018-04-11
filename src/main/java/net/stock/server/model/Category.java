package net.stock.server.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    public Category() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq_gen")
    @SequenceGenerator(name = "categories_seq_gen", sequenceName = "categories_id_seq")
    private long id;
    @Column(name = "food", nullable = false)
    private boolean food;
    @Column(name = "household_items", nullable = false)
    private boolean householdItems;
    @Column(name = "clothes", nullable = false)
    private boolean clothes;
    @Column(name = "appliances", nullable = false)
    private boolean appliances;

    @Column(name = "user_id", nullable = false)
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isHouseholdItems() {
        return householdItems;
    }

    public void setHouseholdItems(boolean householdItems) {
        this.householdItems = householdItems;
    }

    public boolean isClothes() {
        return clothes;
    }

    public void setClothes(boolean clothes) {
        this.clothes = clothes;
    }

    public boolean isAppliances() {
        return appliances;
    }

    public void setAppliances(boolean appliances) {
        this.appliances = appliances;
    }
}
