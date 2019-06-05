package com.example.tpdm_unidad5_practica1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Here in MainActivity we will write code for asking permission
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    ConexionBase base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText msj, num;
        base = new ConexionBase(this, "BDFrases", null, 1);
        insertar_datos();
        solicitarPermisos();
        //permisos_enviar();
        msj = findViewById(R.id.msj);
        num = findViewById(R.id.num);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            msj.setText(extras.getString("message"));
            num.setText(extras.getString("number"));
        }
    }

    void insertar_datos(){
        SQLiteDatabase db = this.base.getWritableDatabase();
        db.execSQL("INSERT INTO Frases VALUES('1','No te tomes la vida demasiado en serio. Nunca saldrás vivo de ella.')");
        db.execSQL("INSERT INTO Frases VALUES('2','Lo importante no es saber, sino tener el teléfono del que sabe.')");
        db.execSQL("INSERT INTO Frases VALUES('3','Si pudieses patear a la persona responsable de la mayoría de tus problemas, no podrías sentarte en un mes.')");
        db.execSQL("INSERT INTO Frases VALUES('4','No renuncies a tus sueños... Sigue durmiendo.')");
        db.execSQL("INSERT INTO Frases VALUES('5','Mi cama y yo nos amamos, pero obviamente la alarma está celosa.')");
        db.execSQL("INSERT INTO Frases VALUES('6','Recuerda siempre que eres absolutamente único. Justo como todos los demás.')");
        db.execSQL("INSERT INTO Frases VALUES('7','Siempre quise ser alguien, pero ahora me doy cuenta que debería haber sido más específico')");
        db.execSQL("INSERT INTO Frases VALUES('8','La felicidad es tener una familia grande, amorosa, dedicada, unida y que viva en otra ciudad.')");
        db.execSQL("INSERT INTO Frases VALUES('9','No te preocupes sobre evitar las tentaciones. Mientras te hagas mayor ellas te evitarán.')");
        db.execSQL("INSERT INTO Frases VALUES('10','Puede que haya bebido demasiado, pero yo mañana volveré a la normalidad y tú seguirás siendo fea.')");
        db.execSQL("INSERT INTO Frases VALUES('11','Si te casas conmigo, juro no mirar a otros orcos.')");
        db.execSQL("INSERT INTO Frases VALUES('12','La vida no es un cuento de hadas. Si pierdes un zapato a media noche no eres una princesa, eres una borracha')");
        db.execSQL("INSERT INTO Frases VALUES('13','Todos deberíamos creer en algo. Yo creo que tomare otra copa')");
        db.execSQL("INSERT INTO Frases VALUES('14','El trabajo en equipo es esencial... te permite echarle la culpa a otro.')");
        db.execSQL("INSERT INTO Frases VALUES('15','Errar es humano, pero echarle la culpa a otro es mas humano todavía')");
        db.execSQL("INSERT INTO Frases VALUES('16','Felices los que nada esperan, porque nunca serán defraudados')");
        db.execSQL("INSERT INTO Frases VALUES('17','La esclavitud no se abolió, se cambió a 8 horas diarias')");
        db.execSQL("INSERT INTO Frases VALUES('18','La inteligencia me persigue, pero yo soy más rápido')");
        db.execSQL("INSERT INTO Frases VALUES('19','Lo importante no es ganar, sino hacer perder al otro.')");
        db.execSQL("INSERT INTO Frases VALUES('20','Todo es divertido, siempre y cuando le ocurra a otra persona')");

    }

    private void solicitarPermisos() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_SMS},3);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.RECEIVE_SMS},4);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_PHONE_STATE},1);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS},2);
        }
    }
}
