package com.harel.practise.inteviews.evercompliant.dal;

import com.harel.practise.inteviews.evercompliant.model.Item;
import com.harel.practise.inteviews.evercompliant.model.ItemType;

import java.util.List;

public interface ItemsRepo {

    List<Item> fetchBy(ItemType itemType);
}
