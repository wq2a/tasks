package com.dreamycity.ali.dao;

import java.util.ArrayList;

import com.dreamycity.ali.model.Item;
import com.dreamycity.ali.model.AliOrder;

public interface ItemDAO {
    public void insert(AliOrder aliOrder);
    public int select(Item item);
}
