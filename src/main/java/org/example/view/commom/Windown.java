package org.example.view.commom;

import javax.swing.*;
import java.awt.*;

public abstract class Windown extends JFrame {
    public Windown(){
        this.setTitle("aps  fingerSprint");
        this.setDefaultCloseOperation(3);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true);
    }

    public void addAll(Component... components) {
        for (Component component : components) {
            this.add(component);
        }
    }

    public void removeAll(Component... components) {
        for (Component component : components) {
            this.remove(component);
        }
    }

}
