package com.harel.practise.inteviews.ever.dal;

import com.harel.practise.inteviews.ever.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemsRepo implements ItemsRepo {

    private Map<ItemType, List<Item>> itemsMap = new HashMap<>();

    public InMemoryItemsRepo() {
        List<Item> toilets = Arrays.asList(new Toilet(new Coordinate(1, 1)), new Toilet(new Coordinate(2, 2)));
        List<Item> restaurants = Arrays.asList(new Restaurant(new Coordinate(1, 1)), new Restaurant(new Coordinate(2, 2)));
        itemsMap.put(ItemType.Restaurant, restaurants);
        itemsMap.put(ItemType.Toilet, toilets);
    }

    @Override
    public List<Item> fetchBy(ItemType itemType) {
        return itemsMap.get(itemType);
    }
}
