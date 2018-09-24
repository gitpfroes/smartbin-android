package br.pucminas.smartbin.persistencia;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LixeiraDatabase {
    private static DatabaseReference firebase;
    public static DatabaseReference getFirebaseDatabase() {

        if (firebase == null) {
            //conexão com o banco de dados
            firebase = FirebaseDatabase.getInstance().getReference("lixeiras");
        }

        return firebase;
    }
}
