package com.jf2mc1.a015004vinstagramclone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private Button btnSave, btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edt_name);
        edtPunchSpeed = findViewById(R.id.edt_punch_speed);
        edtPunchPower = findViewById(R.id.edt_punch_power);
        edtKickSpeed = findViewById(R.id.edt_kick_speed);
        edtKickPower = findViewById(R.id.edt_kick_power);
        btnSave = findViewById(R.id.btn_save);
        btnClear = findViewById(R.id.btn_clear);

        btnSave.setOnClickListener(this::onClick);

        btnClear.setOnClickListener(this::onClick);


    }

    private void saveKickBoxer() {
        try {

            ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.valueOf(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.valueOf(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.valueOf(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.valueOf(edtKickPower.getText().toString()));


            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this,
                                kickBoxer.get("name") + " :)",
                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {
                        FancyToast.makeText(SignUp.this,
                                e.getMessage() + " :(",
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(SignUp.this,
                    e.getMessage() + " :(",
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }

    private void clearFields() {
        edtName.setText("");
        edtPunchSpeed.setText("");
        edtPunchPower.setText("");
        edtKickSpeed.setText("");
        edtKickPower.setText("");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_save:
            saveKickBoxer();
            break;

           case R.id.btn_clear:
            clearFields();
            break;

            default:
                break;

        }

    }
}