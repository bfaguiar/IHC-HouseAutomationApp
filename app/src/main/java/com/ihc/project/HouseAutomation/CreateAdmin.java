package com.ihc.project.HouseAutomation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CreateAdmin  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    static ArrayList<User> usersAdmin = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        User user = new User("admin", "admin", "admin", "admin@ihc.ua.pt");
        usersAdmin.add(user);
        saveUsers(usersAdmin);

    }


    public static void saveUsers(ArrayList<User> users) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("saveUsers")));
        oos.writeObject(users);
        oos.close();
    }

}
