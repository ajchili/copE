package com.kirinpatel.cope.gui;

import com.kirinpatel.cope.utils.SystemInfo;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class DriveSelectorPanel extends JPanel {

    private static final DriveSelectorPanel INSTANCE = new DriveSelectorPanel();
    private static JComboBox<Object> driveSelector;

    private DriveSelectorPanel() {
        super(new BorderLayout());
        driveSelector = new JComboBox<>(SystemInfo.getDrives().toArray());
        driveSelector.addActionListener(e -> {
            File selectedPath = getSelectedDrive();
            if (!SystemInfo.canTransfer(selectedPath, InteractionPanel.getCopyLocation())) {
                System.out.println("AAAAA " + selectedPath);
            }
        });
        add(new JLabel("Please select a drive to copy:"), BorderLayout.NORTH);
        add(driveSelector, BorderLayout.CENTER);
    }

    static DriveSelectorPanel getInstance() {
        return INSTANCE;
    }

    static File getSelectedDrive() {
        return (File) driveSelector.getSelectedItem();
    }
}
