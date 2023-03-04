package org.example.view;

import org.example.model.Person;
import org.example.model.Tables;
import org.example.repository.TablesRepository;
import org.example.service.DBConnectionService;
import org.example.view.commom.Windown;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Home extends Windown {
    private TextField query = new TextField();
    private Person person = new Person();
    private TablesRepository tablesRepository = new TablesRepository();
    private DBConnectionService dbConnectionService = new DBConnectionService();

    private JTable tabela;
    private JPanel tablePanel = new JPanel();
    JList<String> list = new JList<>();

    public Home(Person person) throws SQLException {
        this.setLayout(null);
        this.person = person;
        loadComponents();
    }

    private void loadComponents() throws SQLException {

        JScrollPane scroll  = new JScrollPane();
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(1,1));
        panel.setBounds(25, query.getY() + 50, Toolkit.getDefaultToolkit().getScreenSize().width - 150, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        scroll.setBounds(25, query.getY() + 50, Toolkit.getDefaultToolkit().getScreenSize().width - 250, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        panel.add(scroll);
        this.addAll(loadTableList());
        loadLabel();
        this.list.setSelectedIndex(0);
        this.add(loadTable());


        loadSearchButton();

        this.repaint();
        this.revalidate();
        this.repaint();

    }

    private void loadSearchButton() {
        JButton search = new JButton("pesquisar");
        search.setBounds(200, 50, 100, 50);
        search.addActionListener(l->{
            try {
                this.onClick();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            revalidate();
            repaint();
        });
        this.add(search);

    }

    /**
     * load query label
     */
    private void loadLabel(){
        this.query.setBounds(10, 60, 100, 30);
        Label queryLabel = new Label();
        queryLabel.setBounds(query.getX(), query.getY() - 20, 50, 30);
        queryLabel.setText("query:");
        this.addAll(queryLabel, query);
    }

    private Component loadTable() throws SQLException {
        try {
            // pre define query
            String query = "select * from " + this.list.getSelectedValue();
            // if query field is not black
            if (this.query != null && !this.query.getText().isBlank()) {
                query = query + " where " + this.query.getText();
            } else {
                query = query + " limit 10";
            }
            // get data
            Map map = this.dbConnectionService.select(query);
            // get cell data
            Object[][] dados = (Object[][]) ((List<Object[]>) map.get("data")).toArray(new Object[][]{});
            String[] colunas = (String[]) ((List) map.get("fields")).toArray(new String[]{});
            // configure table container
            tablePanel.setLayout(new GridLayout(1, 1));
            this.tabela = new JTable(dados, colunas);
            JScrollPane barraRolagem = new JScrollPane(this.tabela);
            tablePanel.add(barraRolagem);
            tablePanel.setBounds(
                    100,
                    250,
                    Toolkit.getDefaultToolkit().getScreenSize().width - 500,
                    Toolkit.getDefaultToolkit().getScreenSize().height - 400);
            // add tables panel in this container
            this.add(tablePanel);
            return tablePanel;
        } catch (SQLException sql) {
            JOptionPane.showConfirmDialog(this, sql.getMessage());
            return tablePanel;

        }


    }

    /**
     * loads a list of all tables allowed to the user
     * @return component in list format
     * @throws SQLException
     */
    public Component loadTableList() throws SQLException {
        // position the list
        this.list.setBounds(0,  0, 50,100);
        // get tables allowed to the user
        List<Tables> tablesAllowed = this.tablesRepository.findAll();
        // crate name list
        List<String> names = new ArrayList<>();

        tablesAllowed.forEach( t -> {
            // if table level is lower than person level
            if (t.getLevel() <= this.person.getLevel()) {
                // add name in list
                names.add(t.getName());
            }
        });
        // add names in component list
        this.list.setListData(names.toArray(new String[]{}));
        //create new container
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        JScrollPane scroll = new JScrollPane(list);
        panel.add(scroll);
        panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width-300,60, 200, 100);
        // return panel
        return panel;

    }

    /**
     * set action on search button component
     * @throws SQLException
     */
    private void onClick() throws SQLException {
        // remove table panel in this frame
        this.remove(tablePanel);
        // remove table and table panel in Windown component
        this.removeAll(tablePanel, tabela);
        // if the table pane has more than one component
        if (this.tablePanel.getComponentCount() > 0)
            // remove the first component before load table
            this.tablePanel.remove(0);
        // show new table with search results
        this.loadTable();

    }

    public static void main(String[] args) throws SQLException {
       var person = new Person();
        person.setLevel(2);
        new Home(person);
    }


}
