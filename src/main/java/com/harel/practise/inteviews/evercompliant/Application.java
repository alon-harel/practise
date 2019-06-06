package com.harel.practise.inteviews.evercompliant;

import com.harel.practise.inteviews.evercompliant.dal.InMemoryItemsRepo;
import com.harel.practise.inteviews.evercompliant.model.Coordinate;
import com.harel.practise.inteviews.evercompliant.model.Item;
import com.harel.practise.inteviews.evercompliant.model.ItemType;


// Write a service that receives a coordinate and returns the closest item to this coordinate.
public class Application {

    public static void main(String[] args) {
        final FinderService finderService = new FinderService(new InMemoryItemsRepo());
        FinderCtrl finderCtrl = new FinderCtrl(finderService);

        Item item = finderCtrl.findClosestTo(new Coordinate(3, 3), ItemType.Restaurant);

        System.out.println(item);
    }
}
