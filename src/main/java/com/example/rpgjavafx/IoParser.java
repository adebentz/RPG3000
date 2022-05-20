package com.example.rpgjavafx;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class IoParser {


    public String askString(String question) {
        System.out.print(question);
        TextInputDialog dialog = new TextInputDialog("");

        dialog.setTitle("Question");
        dialog.setHeaderText(question);
        dialog.setContentText("Réponse: ");

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    public int askInt(String question) {
        return Integer.parseInt(askString(question + " : "));
    }

    public int askInt(String question, int min, int max) {
        int res = askInt(question);
        while (res < min || res > max) {
            print("Entrez un nombre entre " + min + " et " + max + ".");
            res = askInt(question);
        }
        return res;
    }

    public boolean askBoolean(String question) {
        return askString(question).matches("[oyOY]");
    }

    public void print(String message) {
        System.out.println(message);
        Alert dialogPane = new Alert(Alert.AlertType.INFORMATION);
        dialogPane.setTitle("Information");
        dialogPane.setContentText(message);
        dialogPane.showAndWait();
    }
}
