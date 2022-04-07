package com.example.cartasvshumanidad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    //Atributos iniciales de la clase Registro
    private EditText nombre;
    private EditText email;
    private EditText contrsena;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private Button registrar;
    private String nombreStr;
    private String emailStr;
    private String contrasenaStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        nombre=(EditText) findViewById(R.id.txtUsuario);
        email=(EditText) findViewById(R.id.txtRegisterEmail);
        contrsena=(EditText)findViewById(R.id.txtRegisterPassword);
        registrar = (Button) findViewById(R.id.btnRegistrar);

        //Asigno al botón registrar el siguiente método en el que recopilamos los datos introducidos por el usuario y en caso de que no haya problemas invvocamos al método registeruser
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // asiganamos las variables para que sean rellenadas
                nombreStr = nombre.getText().toString();
                emailStr = email.getText().toString();
                contrasenaStr = contrsena.getText().toString();
                //hacemos un if para que en caso de que los campos esten completos se lanze el metodo registeruser
                if (!nombreStr.isEmpty() && !emailStr.isEmpty() && !contrasenaStr.isEmpty()){

                    if(contrasenaStr.length()>=6){
                        registerUser();
                    }
                    //un else para que cuando la contraseña no tenga mas de 6 caracteres salte un toast
                    else{
                        Toast.makeText(Registro.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                // salta un toast si los campos no estan completos
                else {
                    Toast.makeText(Registro.this, "Campos incompletos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Método en el que mediante los datos introducidos por el usuario estos son registrados en el firebase
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(emailStr,contrasenaStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //si no hay ningun error se ejecutará lo siguiente y te llevará a la activity login
                    startActivity(new Intent(Registro.this , Login.class));
                    finish();
                    // creamos un hash map para guardar los campos en la base de datos de firebase
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre",nombreStr);
                    map.put("email",emailStr);
                    String id= mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                progressDialog.setMessage("Registrando...");
                                progressDialog.show();
                                startActivity(new Intent(Registro.this , Login.class));
                                finish();
                            }
                            else{
                                Toast.makeText(Registro.this, "No se han podido crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(Registro.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    //Constructor vacio de la clase Registro
    public Registro() {
    }

    //Constructor complesto de la clase Registro
    public Registro(EditText nombre, EditText email, EditText contrsena, FirebaseAuth mAuth, DatabaseReference mDatabase, ProgressDialog progressDialog, Button registrar, String nombreStr, String emailStr, String contrasenaStr) {
        this.nombre = nombre;
        this.email = email;
        this.contrsena = contrsena;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
        this.progressDialog = progressDialog;
        this.registrar = registrar;
        this.nombreStr = nombreStr;
        this.emailStr = emailStr;
        this.contrasenaStr = contrasenaStr;
    }


  //Metodos Get y Set de la clase Registro
    public EditText getNombre() {
        return nombre;
    }

    public void setNombre(EditText nombre) {
        this.nombre = nombre;
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getContrsena() {
        return contrsena;
    }

    public void setContrsena(EditText contrsena) {
        this.contrsena = contrsena;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void setmDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public Button getRegistrar() {
        return registrar;
    }

    public void setRegistrar(Button registrar) {
        this.registrar = registrar;
    }

    public String getNombreStr() {
        return nombreStr;
    }

    public void setNombreStr(String nombreStr) {
        this.nombreStr = nombreStr;
    }

    public String getEmailStr() {
        return emailStr;
    }

    public void setEmailStr(String emailStr) {
        this.emailStr = emailStr;
    }

    public String getContrasenaStr() {
        return contrasenaStr;
    }

    public void setContrasenaStr(String contrasenaStr) {
        this.contrasenaStr = contrasenaStr;
    }

    /**
     * Metodo que sirve para devolver todos los atributos de la clase
     * @return devuelve todos los atributos de la clase en formato de cadena de texto
     */
    @Override
    public String toString() {
        return "Registro{" +
                "nombre=" + nombre +
                ", email=" + email +
                ", contrsena=" + contrsena +
                ", mAuth=" + mAuth +
                ", mDatabase=" + mDatabase +
                ", progressDialog=" + progressDialog +
                ", registrar=" + registrar +
                ", nombreStr='" + nombreStr + '\'' +
                ", emailStr='" + emailStr + '\'' +
                ", contrasenaStr='" + contrasenaStr + '\'' +
                '}';
    }

    /**
     * Metodo que sirve para cancelar y volver a la clase Login
     * @param view
     */
    public void cancelar(View view)
    {
        Intent i= new Intent(this,Login.class);
        startActivity(i);
    }
}