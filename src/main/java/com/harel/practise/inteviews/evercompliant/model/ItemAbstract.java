package com.harel.practise.inteviews.evercompliant.model;

public abstract class ItemAbstract implements Item {

    private Coordinate coordinate;
    private ItemType itemType;

    public ItemAbstract(Coordinate coordinate, ItemType itemType) {
        this.coordinate = coordinate;
        this.itemType = itemType;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public ItemType getType() {
        return itemType;
    }

    @Override
    public String toString() {
        return "ItemAbstract{" +
                "coordinate=" + coordinate +
                ", itemType=" + itemType +
                '}';
    }
}
