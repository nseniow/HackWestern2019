package com.example.hackwestern19;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginInformationStorer {

    static String TAG = "LoginInformationStorer";

    public static void storeLoginInformation(String email, String password, Context context){
        PrintWriter out = null;

        try {
            OutputStream outStream = context.openFileOutput("login.txt", Context.MODE_PRIVATE);
            out = new PrintWriter(outStream);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error encountered trying to open file for writing: " + "login.txt");
        }
        out.println("email");
        out.println("password");
        out.close();
    }

    private static User getUserFromFile(Context context) {

        User user = new User("", "", "", "");

        try (Scanner scanner = new Scanner(context.openFileInput("login.txt"))) {
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (i == 0){
                    user.setEmail(line);
                    i++;
                } else if (i == 1){
                    user.setPassword(line);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Error encountered trying to open file for reading: " + "login.txt");
        }

        return user;
    }
}
