package org.example.ui.db;

import org.example.enums.StorageType;
import org.example.ui.BaseBookUI;

public class DBBookUI extends BaseBookUI {

    public DBBookUI() {
        super(StorageType.DATABASE, "DATABASE");
    }
}
