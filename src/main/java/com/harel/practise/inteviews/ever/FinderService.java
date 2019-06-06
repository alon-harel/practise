package com.harel.practise.inteviews.ever;

import com.harel.practise.inteviews.ever.dal.ItemsRepo;
import com.harel.practise.inteviews.ever.model.Coordinate;
import com.harel.practise.inteviews.ever.model.Item;
import com.harel.practise.inteviews.ever.model.ItemType;

import java.util.List;

public class FinderService {

    private ItemsRepo itemsRepo;

    public FinderService(ItemsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    public Item find(Coordinate coordinate, ItemType type) {
        List<Item> items = itemsRepo.fetchBy(type);

        double smallestDistance = Double.MAX_VALUE;
        Item retItem = null;
        for (Item item : items) {
            final double distanceFromItem = item.getCoordinate().distanceFrom(coordinate);
            if (distanceFromItem < smallestDistance) {
                smallestDistance = distanceFromItem;
                retItem = item;
            }
        }
        // Handle case where no item was found.
        return retItem;
    }
}
