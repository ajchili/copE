package com.kirinpatel.cope.gui;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class ProgressWindow extends JFrame {

    private Path transferLocation;

    ProgressWindow(Path transferLocation) {
        super("copE progress");
        this.transferLocation = transferLocation;
        setSize(new Dimension(300, 150));
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
