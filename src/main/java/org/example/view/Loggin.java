package org.example.view;

import org.example.model.Person;
import org.example.service.FingerSprintService;
import org.example.service.PersonService;
import org.example.view.commom.Windown;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Loggin extends Windown {

    private TextField email  = new TextField();
    private File image;
    private Person person;


    private FingerSprintService fingerSprintService = new FingerSprintService();
    private PersonService personService = new PersonService();

    public Loggin() {
        this.setLayout(null);
        loadComponents();

    }
    private void loadComponents(){
       // email box
        this.email.setBounds(450, 40, 350, 30);
        Label email_Label = new Label();
        email_Label.setBounds(email.getX(), email.getY()- 20, 250, 30 );
        email_Label.setText("email:");


        // fingerSprint Box

        JButton search = new JButton();
        search.setText("logar");
        search.setBounds(email_Label.getX(), email_Label.getY() + 200, 500, 500);
        search.addActionListener(event -> {
            onClick();
        });
        this.addAll(email, email_Label, search);

    }

    private void onClick() {

           JFileChooser chooser = new JFileChooser();
           chooser.setDialogTitle("selecione uma digital");
           int condition = chooser.showOpenDialog(this);
           if (condition == JFileChooser.APPROVE_OPTION) {
               image = chooser.getSelectedFile();
               System.out.println(image);

           }

           Person person = new Person();
           if (this.email.getText().contains("@")){
               person.setEmail(this.email.getText());
           } else {
               person.setCpf(this.email.getText());
           }

           person = personService.findPersonByEmailOrCPF(person);
           if (person != null) {
               new Home(person);
               this.dispose();
           } else {
               this.image = null;
           }
            System.out.println(person);




    }



}
