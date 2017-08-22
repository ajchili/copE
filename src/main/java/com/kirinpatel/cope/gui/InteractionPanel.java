package com.kirinpatel.cope.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class InteractionPanel extends JPanel {

    private static final InteractionPanel INSTANCE = new InteractionPanel();
    private static JTextField locationField;

    private InteractionPanel() {
        super(new BorderLayout());
        add(new JLabel("Please select a location to copy to:"), BorderLayout.NORTH);
        JPanel locationSelectionPanel = new JPanel(new BorderLayout());
        locationField = new JTextField();
        locationSelectionPanel.add(locationField, BorderLayout.CENTER);
        JButton selectLocationButton = new JButton("...");
        selectLocationButton.addActionListener(e -> {

        });
        locationSelectionPanel.add(selectLocationButton, BorderLayout.EAST);
        add(locationSelectionPanel, BorderLayout.CENTER);
        JButton start = new JButton("copE");
        start.addActionListener(e -> {
            if (getCopyLocation() == null) {
                JOptionPane.showMessageDialog(this, "No location provided to copy data to.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(start, BorderLayout.SOUTH);
    }

    static InteractionPanel getInstance() {
        return INSTANCE;
    }

    static File getCopyLocation() {
        return locationField.getText().isEmpty() ? null : new File(locationField.getText());
    }
}
