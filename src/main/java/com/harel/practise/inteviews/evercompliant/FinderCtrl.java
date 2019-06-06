package com.harel.practise.inteviews.evercompliant;

import com.harel.practise.inteviews.evercompliant.model.Coordinate;
import com.harel.practise.inteviews.evercompliant.model.Item;
import com.harel.practise.inteviews.evercompliant.model.ItemType;

public class FinderCtrl {

    private FinderService finderService;

    public FinderCtrl(FinderService finderService) {
        this.finderService = finderService;
    }

    public Item findClosestTo(Coordinate coordinate, ItemType type) {
        return finderService.find(coordinate, type);
    }
}
