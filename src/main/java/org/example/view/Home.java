package org.example.view;

import org.example.view.commom.Windown;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Home extends Windown {
    private TextField query = new TextField();

    public Home() {
        this.setLayout(null);
        loadComponents();
    }

    private void loadComponents() {
        // query box
        this.query.setBounds(10, 60, 100, 30);
        Label queryLabel = new Label();
        queryLabel.setBounds(query.getX(), query.getY() - 20, 50, 30);
        queryLabel.setText("query:");


        // table box
        JScrollPane scroll  = new JScrollPane();
        scroll.setBounds(25, query.getY() + 50, Toolkit.getDefaultToolkit().getScreenSize().width - 150, Toolkit.getDefaultToolkit().getScreenSize().height - 200);

        this.addAll( query, queryLabel, scroll);
        this.repaint();
        this.revalidate();
        this.repaint();

    }

    private JTable loadTable(){
        JTable table = new JTable();
        TableColumn tc = new TableColumn();

        return table;
    }

    public static void main(String[] args) {
        new Home();
    }
}
