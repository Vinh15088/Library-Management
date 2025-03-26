package org.example.ui.db;

import org.example.enums.StorageType;
import org.example.ui.BaseUserUI;

public class DBUserUI extends BaseUserUI {
    public DBUserUI() {
        super(StorageType.DATABASE, "DATABASE");
    }
}
