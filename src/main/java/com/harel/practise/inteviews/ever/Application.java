package com.harel.practise.inteviews.ever;

import com.harel.practise.inteviews.ever.FinderCtrl;
import com.harel.practise.inteviews.ever.FinderService;
import com.harel.practise.inteviews.ever.dal.InMemoryItemsRepo;
import com.harel.practise.inteviews.ever.model.Coordinate;
import com.harel.practise.inteviews.ever.model.Item;
import com.harel.practise.inteviews.ever.model.ItemType;

public class Application {

    public static void main(String[] args) {
        final FinderService finderService = new FinderService(new InMemoryItemsRepo());
        FinderCtrl finderCtrl = new FinderCtrl(finderService);

        Item item = finderCtrl.findClosestTo(new Coordinate(3, 3), ItemType.Restaurant);

        System.out.println(item);
    }
}
