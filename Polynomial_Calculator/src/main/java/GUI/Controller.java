package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private Model model;
    private String P1;
    private String P2;
    private String operatie;
    private String rezultat;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        P1 = new String("");
        P2 = new String("");
        operatie = view.getOperatie();
        rezultat = new String("n/a");

        //asculatorii
        view.addCalculeazaListener(new whenCalculeazaPressed());
        view.addResetListener(new whenResetPressed());
    }
    class whenCalculeazaPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            P1 = view.getPolinom(1);
            P2 = view.getPolinom(2);
            model.setModel(P1, P2);
            operatie = view.getOperatie();
            //efectueaza calculele: functie din model(P1, P2, operatie) care sa returneze Stringul obtinut din polinom
            if(operatie.equals("Adunare"))
                rezultat = model.Adunare();
            if(operatie.equals("Scădere"))
                rezultat = model.Scadere();
            if(operatie.equals("Înmulțire"))
                rezultat = model.Inmultire();
            if(operatie.equals("Împărțire")) {
                rezultat = model.Impartire();
                if(rezultat.equals("Eroare"))
                    view.dialogBox("Eroare! Ați încercat împărțirea un polinom la 0");
                if(rezultat.equals("Eroare 2"))
                {
                    view.dialogBox("Eroare! Aveți cazul de nedeterminare 0/0");
                    rezultat = "Eroare";
                }
            }
            if(operatie.equals("Integrare (a P1)"))
                rezultat = model.Integrare();
            if(operatie.equals("Derivare (a P1)"))
                rezultat = model.Derivare();
            view.setResult(rezultat);
        }
    }
    class whenResetPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            P1 = "";
            P2 = "";
            operatie = "";
            rezultat = "";
            view.resetP1();
            view.resetP2();
            view.getMenuOp().setSelectedIndex(0);
            view.setResult(rezultat);
        }
    }

}
