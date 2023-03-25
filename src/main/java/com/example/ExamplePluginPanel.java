package com.example;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

import java.awt.*;

public class ExamplePluginPanel extends PluginPanel {

    private static final Border BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public ExamplePluginPanel() {
        super(false);
        setLayout(new BorderLayout());
        setBorder(BORDER);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        titlePanel.setLayout(new BorderLayout());

        String title = "My Plugin Title";
        JLabel pluginTitleLabel = new JLabel(title);
        pluginTitleLabel.setForeground(ColorScheme.LIGHT_GRAY_COLOR);
        pluginTitleLabel.setFont(FontManager.getRunescapeBoldFont());
        pluginTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        titlePanel.add(pluginTitleLabel, BorderLayout.WEST);

        add(titlePanel, BorderLayout.NORTH);

        // Add additional components to the panel here...
    }
}
