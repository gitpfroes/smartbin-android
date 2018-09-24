package br.pucminas.smartbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.pucminas.smartbin.model.Lixeira;
import br.pucminas.smartbin.model.TipoLixeira;
import br.pucminas.smartbin.persistencia.LixeiraDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference lixeiraFirebase;
    private List<Lixeira> lixeiras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lixeiraFirebase = LixeiraDatabase.getFirebaseDatabase();
        loadListLixeiras();
    }

    public void iniciarJornada(View view){
        Intent intent = new Intent(MainActivity.this, RotaActivity.class);
        intent.putExtra(RotaActivity.LISTA_LIXEIRAS, (Serializable) lixeiras);
        startActivity(intent);
    }

    private void loadListLixeiras(){
        //addValueEventListener recupera os dados automaticamente quando alterar o banco de dados
        lixeiraFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lixeiras = new ArrayList<>();

                int num = 0;

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Lixeira lixeira = new Lixeira();
                    lixeira.setId(postSnapshot.child("id").getValue().toString());
                    lixeira.setTipo(postSnapshot.child("tipo").getValue().toString());
                    lixeira.setLatitude(Double.parseDouble(postSnapshot.child("latitude").getValue().toString()));
                    lixeira.setLongitude(Double.parseDouble(postSnapshot.child("longitude").getValue().toString()));
                    lixeiras.add(lixeira);

                    lixeira = null;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //Simula massa de dados
    private void geraMassaDados(){
        Lixeira lixA = new Lixeira();
        lixA.setId("1");
        lixA.setTipo(TipoLixeira.PLASTICO.toString());
        //Praça da Estação
        lixA.setLatitude(-19.9159578);
        lixA.setLongitude(-43.9387856);
        lixA.setPeso(50);
        lixA.setVolume(300);
        cadastrarLixeira(lixA);

        Lixeira lixB = new Lixeira();
        lixB.setId("2");
        lixB.setTipo(TipoLixeira.PAPEL.toString());
        //Praça da Assembléia
        lixB.setLatitude(-19.932275);
        lixB.setLongitude(-43.9480725);
        lixB.setPeso(60);
        lixB.setVolume(220);
        cadastrarLixeira(lixB);

        Lixeira lixC = new Lixeira();
        lixC.setId("3");
        lixC.setTipo(TipoLixeira.VIDRO.toString());
        //Praça Governador Israel Pinheiro
        lixC.setLatitude(-19.955656);
        lixC.setLongitude(-43.9319106);
        lixC.setPeso(40);
        lixC.setVolume(200);
        cadastrarLixeira(lixC);

        Lixeira lixD = new Lixeira();
        lixD.setId("4");
        lixD.setTipo(TipoLixeira.METAL.toString());
        //BH Shopping
        lixD.setLatitude(-19.9747505);
        lixD.setLongitude(-43.9469659);
        lixD.setPeso(70);
        lixD.setVolume(500);
        cadastrarLixeira(lixD);

        Lixeira lixE = new Lixeira();
        lixE.setId("5");
        lixE.setTipo(TipoLixeira.METAL.toString());
        //Boulevard Shopping
        lixE.setLatitude(-19.9206343);
        lixE.setLongitude(-43.9223219);
        lixE.setPeso(100);
        lixE.setVolume(620);
        cadastrarLixeira(lixE);
    }

    //Cria/Atualiza as lixeiras no banco
    private void cadastrarLixeira(final Lixeira lixeira){

        lixeiraFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //verifica se o contato já está cadastrado
                boolean contatoJaCadastrado = dataSnapshot.hasChild(lixeira.getId());

                if (contatoJaCadastrado) {
                    Toast.makeText(getApplicationContext(), "Lixeira já cadastrada", Toast.LENGTH_SHORT).show();
                } else {
                    lixeiraFirebase.child(lixeira.getId()).setValue(lixeira);
                    Toast.makeText(getApplicationContext(), "Lixeira cadastrada com sucesso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
