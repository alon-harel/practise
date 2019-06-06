package com.harel.practise.inteviews.ever;

import com.harel.practise.inteviews.ever.model.Coordinate;
import com.harel.practise.inteviews.ever.model.Item;
import com.harel.practise.inteviews.ever.model.ItemType;

public class FinderCtrl {

    private FinderService finderService;

    public FinderCtrl(FinderService finderService) {
        this.finderService = finderService;
    }

    public Item findClosestTo(Coordinate coordinate, ItemType type) {
        return finderService.find(coordinate, type);
    }
}
