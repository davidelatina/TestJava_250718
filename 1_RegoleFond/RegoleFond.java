/*
 Esercizio 1 – Le Tre Regole Fondamentali dell’OOP 
Obiettivo: Applicare incapsulamento, ereditarietà e polimorfismo

Progetta un semplice sistema per la gestione di veicoli. Crea una classe base Veicolo che contenga almeno due attributi (es. marca, anno) e un metodo dettagli().
 Applica l'incapsulamento rendendo gli attributi privati e usando getter/setter. 
 Crea due sottoclassi: Auto e Moto, che ereditano da Veicolo. 
 Sovrascrivi il metodo dettagli() nelle sottoclassi per polimorfismo.
 Infine, nel main, crea un arraylist di Veicolo e stampa i dettagli di ogni elemento.
Gestisci il tutto tramite menu
 */

import java.util.*;

public class RegoleFond {
  public static void main(String[] args) {
    Scanner scanNum = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    ArrayList<Veicolo> parcoAuto = new ArrayList<>();
    parcoAuto.add(new Auto("Fiat", "Panda", 2020));

    int inputUtenteInt = -1;

    int inputUtenteAnno = -1;
    String inputUtenteMarca = "";
    String inputUtenteModello = "";

    while (true) { // Ciclo principale programma. Uscita con input utente <= 0

      System.out.println("");
      System.out.println("   GESTIONE AUTOOFFICINA");
      System.out.println("0. Esci");
      System.out.println("1. Aggiungi automobile");
      System.out.println("2. Aggiungi motocicletta");
      System.out.println("3. Stampa veicoli");

      System.out.print("Scelta: ");

      inputUtenteInt = scanNum.nextInt();
      scanNum.nextLine(); // Smaltire newline

      if (inputUtenteInt <= 0) { // <-------------------------- USCITA LOOP E PROGRAMMA
        break;
      }

      switch (inputUtenteInt) {
        case 1: // Aggiungi automobile

          // Input utente
          System.out.print("Inserire marca: ");
          inputUtenteMarca = scanStr.nextLine();
          System.out.print("Inserire modello: ");
          inputUtenteModello = scanStr.nextLine();
          System.out.print("Inserire anno: ");
          inputUtenteAnno = scanNum.nextInt();
          scanNum.nextLine();

          // Inserimento
          parcoAuto.add(new Auto(inputUtenteMarca, inputUtenteModello, inputUtenteAnno));
          break;

        case 2: // Aggiungi motocicletta

          // Input utente
          System.out.print("Inserire marca: ");
          inputUtenteMarca = scanStr.nextLine();
          System.out.print("Inserire modello: ");
          inputUtenteModello = scanStr.nextLine();
          System.out.print("Inserire anno: ");
          inputUtenteAnno = scanNum.nextInt();
          scanNum.nextLine();

          // Inserimento
          parcoAuto.add(new Moto(inputUtenteMarca, inputUtenteModello, inputUtenteAnno));
          break;

        case 3: // Stampa veicoli
          for (Veicolo v : parcoAuto) {
            v.dettagli();
          }
          break;

        default:
          System.out.println("Scelta non valida.");
      }

    }

    scanStr.close();
    scanNum.close();
  }
}

abstract class Veicolo {
  // Variabili d'istanza
  private String marca;
  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  private String modello;

  public String getModello() {
    return modello;
  }

  public void setModello(String modello) {
    this.modello = modello;
  }

  private int anno;

  public int getAnno() {
    return anno;
  }

  public void setAnno(int anno) {
    this.anno = anno;
  }

  // Costruttore
  Veicolo(String marca, String modello, int anno) {
    this.marca = marca;
    this.modello = modello;
    this.anno = anno;
  }

  // Stampa dettagli
  public void dettagli() {
    System.out.print(" | " + this.getMarca() + " " + this.getModello() + " " + this.getAnno());
  }
}

class Auto extends Veicolo {

  // Costruttore
  public Auto(String marca, String modello, int anno) {
    super(marca, modello, anno);
  }

  @Override
  public void dettagli() {
    System.out.print("Automobile");
    super.dettagli();
    System.out.println("");
  }
}

class Moto extends Veicolo {

  // Costruttore
  public Moto(String marca, String modello, int anno) {
    super(marca, modello, anno);
  }

  @Override
  public void dettagli() {
    System.out.print("Motocicletta");
    super.dettagli();
    System.out.println("");
  }
}