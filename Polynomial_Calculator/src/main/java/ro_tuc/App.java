package ro_tuc;

import Data_Models.Polinom;
import GUI.Model;
import GUI.View;
import GUI.Controller;
public class App {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller contr = new Controller(view, model);
    }
}