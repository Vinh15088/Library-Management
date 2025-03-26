package org.example.ui.db;

import org.example.enums.StorageType;
import org.example.ui.BaseAuthorUI;

public class DBAuthorUI extends BaseAuthorUI {
    public DBAuthorUI() {
        super(StorageType.DATABASE, "DATABASE");
    }
}
