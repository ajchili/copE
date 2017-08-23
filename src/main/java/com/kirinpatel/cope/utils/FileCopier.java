package com.kirinpatel.cope.utils;

import com.kirinpatel.cope.gui.ProgressWindow;

import java.io.*;

public class FileCopier {

    public FileCopier(File file, File copyLocation) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                try {
                    new FileCopier(f, copyLocation);
                } catch (NullPointerException e) {
                    // Ignore NullPointerExceptions, these files will not be copied
                }
            }
        } else {
            ProgressWindow.increaseTotalFileCount();
            moveFile(file, copyLocation);
        }
    }

    private void moveFile(File file, File copyLocation) {
        String pathAndFileName = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("\\"));
        File newFile = new File(copyLocation.getAbsolutePath() + pathAndFileName);
        if (!newFile.getParentFile().mkdirs()) {
            ProgressWindow.decreaseTotalFileCount();
            return;
        }
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                ProgressWindow.decreaseTotalFileCount();
                return;
            }
        }
        try (InputStream inStream = new FileInputStream(file);
             OutputStream outStream = new FileOutputStream(newFile)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

        } catch(IOException e) {
            ProgressWindow.decreaseTotalFileCount();
            return;
        }
        ProgressWindow.increaseCopiedFileCount(file, newFile);
    }
}
