package com.kirinpatel.cope.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final Window INSTANCE = new Window();

    private Window() {
        super("copE");
        setSize(new Dimension(250, 175));
        setResizable(false);
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(DriveSelectorPanel.getInstance());
        add(InteractionPanel.getInstance());
    }

    public static Window getInstance() {
        return INSTANCE;
    }
}
