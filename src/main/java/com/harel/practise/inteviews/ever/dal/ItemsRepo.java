package com.harel.practise.inteviews.ever.dal;

import com.harel.practise.inteviews.ever.model.Item;
import com.harel.practise.inteviews.ever.model.ItemType;

import java.util.List;

public interface ItemsRepo {

    List<Item> fetchBy(ItemType itemType);
}
