package com.kirinpatel.cope.gui;

import com.kirinpatel.cope.utils.FileCopier;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProgressWindow extends JFrame {

    private static int originalFileCount = 0;
    private static int totalFiles = 0;
    private static int copiedFiles = 0;
    private File drive;
    private File copyLocation;
    private JLabel progressLabel;
    private static JTextArea copedFilesArea;
    private JProgressBar progressBar;

    ProgressWindow(File drive, File copyLocation) {
        super("copE progress");
        this.drive = drive;
        this.copyLocation = copyLocation;
        setSize(new Dimension(700, 400));
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        progressLabel = new JLabel("Copying files... (" + copiedFiles + "/" + totalFiles + ")");
        add(progressLabel, BorderLayout.NORTH);
        progressBar = new JProgressBar();
        add(progressBar, BorderLayout.CENTER);
        setVisible(true);
        startProcess();
    }

    public static void increaseTotalFileCount() {
        totalFiles++;
        originalFileCount++;
    }

    public static void decreaseTotalFileCount() {
        totalFiles--;
    }

    public static void increaseCopiedFileCount(File file, File newFile) {
        copiedFiles++;
    }

    private void startProcess() {
        new Thread(() -> {
            new FileCopier(drive, copyLocation);
        }).start();

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(500);
                    progressLabel.setText("Copying files... (" + copiedFiles + "/" + totalFiles + ")");
                    progressBar.setMaximum(totalFiles);
                    progressBar.setValue(copiedFiles);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
