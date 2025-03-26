package org.example.ui.file;

import org.example.enums.StorageType;
import org.example.ui.BaseBookUI;


public class FileBookUI extends BaseBookUI {
    public FileBookUI() {
        super(StorageType.FILE, "FILE");
    }
}
