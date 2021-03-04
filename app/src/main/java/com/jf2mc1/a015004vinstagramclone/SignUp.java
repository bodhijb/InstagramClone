package com.jf2mc1.a015004vinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private Button btnSaveKickBoxer, btnClear, btnGetAllDataKb, btnSaveBoxer, btnGetAllDataBx,
    btnTransition;
    private TextView txtGetData;
    private String allKickBoxers, allBoxers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edt_name);
        edtPunchSpeed = findViewById(R.id.edt_punch_speed);
        edtPunchPower = findViewById(R.id.edt_punch_power);
        edtKickSpeed = findViewById(R.id.edt_kick_speed);
        edtKickPower = findViewById(R.id.edt_kick_power);
        txtGetData = findViewById(R.id.txt_get_data);
        btnSaveKickBoxer = findViewById(R.id.btn_save_kb);
        btnSaveBoxer = findViewById(R.id.btn_save_bx);
        btnClear = findViewById(R.id.btn_clear);
        btnGetAllDataKb = findViewById(R.id.btn_get_all_data_kb);
        btnGetAllDataBx = findViewById(R.id.btn_get_all_data_bx);
        btnTransition = findViewById(R.id.btn_transition);

        btnSaveKickBoxer.setOnClickListener(this::onClick);
        btnSaveBoxer.setOnClickListener(this::onClick);
        btnClear.setOnClickListener(this::onClick);
        btnTransition.setOnClickListener(this::onClick);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSeanData();
            }
        });


        btnGetAllDataKb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllKickBoxers();
            }
        });

        btnGetAllDataBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllBoxers();
            }
        });


    }

    private void switchToNextActivity() {

        Intent intent = new Intent(SignUp.this,
                SignUpLoginActivity.class);
        startActivity(intent);

    }

    private void getSeanData() {
        ParseQuery<ParseObject> parseQuery = ParseQuery
                .getQuery("KickBoxer");
        parseQuery.getInBackground("eW0Hnc57LD",
                new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText(object.get("name").toString() +
                                    ": \npunch speed: " + object.get("punchSpeed") + "\npunch power: " + object.get("punchPower")
                                    + "\nkick speed: " + object.get("kickSpeed") + "\nkick power: " + object.get("kickPower"));
                        } else {
                            FancyToast.makeText(SignUp.this, "Oops " + e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
    }


    private void getAllBoxers() {
        allBoxers = "";

        ParseQuery<ParseObject> queryAll =
                ParseQuery.getQuery("Boxer");
        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {

                        for (ParseObject boxer : objects) {
                            allBoxers += boxer.get("name") + " - power: " + boxer.get("punchPower") + ", speed: " + boxer.get("punchSpeed") + "\n";
                        }

                        FancyToast.makeText(SignUp.this,
                                allBoxers, FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this,
                                " list is empty :(", FancyToast.LENGTH_LONG,
                                FancyToast.CONFUSING, true).show();
                    }
                } else {
                    FancyToast.makeText(SignUp.this,
                            " exception :(", FancyToast.LENGTH_LONG,
                            FancyToast.ERROR, true).show();
                }
            }
        });


    }

    private void getAllKickBoxers() {
        allKickBoxers = "";

        ParseQuery<ParseObject> queryAll =
                ParseQuery.getQuery("KickBoxer");

        // filter the query
        //queryAll.whereGreaterThan("punchPower", 10);
        queryAll.whereGreaterThanOrEqualTo("punchPower", 280);
        // only show one result
        queryAll.setLimit(1);


        queryAll.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {

                        for (ParseObject kickboxer : objects) {
                            allKickBoxers += kickboxer.get("name") + "\n";
                        }

                        FancyToast.makeText(SignUp.this,
                                allKickBoxers, FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this,
                                " list is empty :(", FancyToast.LENGTH_LONG,
                                FancyToast.CONFUSING, true).show();
                    }
                } else {
                    FancyToast.makeText(SignUp.this,
                            " exception :(", FancyToast.LENGTH_LONG,
                            FancyToast.ERROR, true).show();
                }
            }
        });


    }


    private void saveBoxer() {
        try {

            ParseObject boxer = new ParseObject("Boxer");
            boxer.put("name", edtName.getText().toString());
            boxer.put("punchSpeed", Integer.valueOf(edtPunchSpeed.getText().toString()));
            boxer.put("punchPower", Integer.valueOf(edtPunchPower.getText().toString()));

            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this,
                                boxer.get("name") + " :)",
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

            case R.id.btn_save_kb:
                saveKickBoxer();
                break;

            case R.id.btn_clear:
                clearFields();
                break;

            case R.id.btn_save_bx:
                saveBoxer();

            case R.id.btn_transition:
                switchToNextActivity();

            default:
                break;

        }

    }


}