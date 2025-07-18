/*
Esercizio 2 – JDBC e Accesso al Database
 Obiettivo: Connessione a un database e recupero dati con JDBC.

Scrivi un programma Java che si connetta a un database MySQL (può essere school, biblioteca, o simile).
 Crea una classe DbManager che gestisca la connessione JDBC. 
 Recupera tutti i record da una tabella a scelta (es. studenti da un  DB che devi creare e riempire) e stampali in console. 
 Prevedi l’uso di PreparedStatement per evitare SQL injection.
 Assicurati di gestire correttamente le eccezioni e di chiudere tutte le risorse (ResultSet, Statement, Connection). 

*/

package com.testjava;

import java.sql.*;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/myscheme";
    static final String USER = "root";
    static final String PASSWORD = "-------";

    public static void main(String[] args) {

        // Initialize DbManager
        DbManager.getInstance();

        // Insert entries
        insertEntry("Aldo", "aldo@email.com");
        insertEntry("Bruno", "bruno@email.com");
        insertEntry("Carlo", "carlo@email.com");
        insertEntry("Daniele", "daniele@email.com");

        // Read from table
        readTable();

    }

    // Insert table entry
    public static void insertEntry(String nome, String email) {
        String sql = "INSERT INTO mytable (nome, email) VALUES (?, ?)";
        try {
            Connection conn = DbManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Utente inserito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read from table
    public static void readTable() {
        String sql = "SELECT * FROM mytable";
        try {
            Connection conn = DbManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Nome: " + rs.getString("nome") +
                                ", Email: " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

class DbManager {

    private static DbManager instance;
    private static Connection conn;

    private DbManager() {
        try {
            conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbManager getInstance() {
        if (instance == null) {
            return new DbManager();
        }
        return instance;
    }

    public static Connection getConnection() {
        return conn;
    }
}