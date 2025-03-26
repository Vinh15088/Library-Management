package org.example.ui.db;

import org.example.enums.StorageType;
import org.example.ui.BaseBorrowUI;

public class DBBorrowUI extends BaseBorrowUI {
    public DBBorrowUI() {
        super(StorageType.DATABASE, "DATABASE");
    }
}
