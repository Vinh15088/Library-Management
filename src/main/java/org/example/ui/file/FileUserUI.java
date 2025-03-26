package org.example.ui.file;

import org.example.enums.StorageType;
import org.example.ui.BaseUserUI;

public class FileUserUI extends BaseUserUI {
    public FileUserUI() {
        super(StorageType.FILE, "FILE");
    }
}
