package com.kirinpatel.cope.utils;

import java.io.File;
import java.util.ArrayList;

public class SystemInfo {

    // Credit: https://stackoverflow.com/q/21059703
    public static ArrayList<File> getDrives() {
        ArrayList<File> files = new ArrayList<>();
        for (File file : File.listRoots()) {
            if (file.getTotalSpace() > 0) {
                files.add(file);
            }
        }
        return files;
    }

    public static boolean canTransfer(File drive, File copyLocation) {
        return copyLocation.getFreeSpace() >= drive.getTotalSpace() - drive.getFreeSpace();
    }
}
