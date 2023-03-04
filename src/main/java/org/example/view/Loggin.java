package org.example.view;

import org.example.model.Person;
import org.example.service.FingerSprintService;
import org.example.service.PersonService;
import org.example.view.commom.Windown;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class Loggin extends Windown {

    private final TextField email = new TextField();
    private File image;
    private Person person;


    private final FingerSprintService fingerSprintService = new FingerSprintService();
    private final PersonService personService = new PersonService();

    public Loggin() {
        this.setLayout(null);
        loadComponents();

    }

    /**
     * load components in this frame
     */
    private void loadComponents() {
        // email box
        this.email.setBounds(450, 40, 350, 30);
        Label email_Label = new Label();
        email_Label.setBounds(email.getX(), email.getY() - 20, 250, 30);
        email_Label.setText("loggin:");
        // fingerSprint Box
        JButton search = new JButton();
        search.setText("logar");
        search.setBounds(email_Label.getX(), email_Label.getY() + 200, 500, 500);
        // add onClick function in search button
        search.addActionListener(event -> {
            try {
                onClick();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        this.addAll(email, email_Label, search);

    }


    private void onClick() throws SQLException {
        try {
            // open folders
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("selecione uma digital");
            int condition = chooser.showOpenDialog(this);
            // if you have a file selected
            if (condition == JFileChooser.APPROVE_OPTION) {
                // get file
                image = chooser.getSelectedFile();
            }

            Person person = new Person();
            // if text field contain @
            if (this.email.getText().contains("@")) {
                // then set person email
                person.setEmail(this.email.getText());
                //  find person by email in database
                person = personService.findPersonByEmailOrCPF(person);
            } else {
                // then set person cpf
                person.setCpf(this.email.getText());
                // find person by cpf in database
                person = personService.findPersonByEmailOrCPF(person);
            }
            // if person not null
            if (person != null) {
                // call frame 'Home'
                new Home(person);
                // close this frame
                this.dispose();
            } else {
                // set selected image to null
                this.image = null;
                JOptionPane.showMessageDialog(this,"verifique as credenciais e tente novamente (email/cpf)", "pessoa não encontrada",1);
            }
        } catch (SQLException sqlException){
            // show dialog error
            JOptionPane.showMessageDialog(this, sqlException.getMessage(), sqlException.getSQLState(), 2);
        }


    }


}
