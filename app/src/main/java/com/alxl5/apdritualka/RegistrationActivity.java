package com.alxl5.apdritualka;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alxl5.apdritualka.data.DBUser;
import com.alxl5.apdritualka.models.User;

public class RegistrationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView userName;
    private TextView userSurname;
    private TextView userPhone;
    private TextView userEmail;
    private EditText editUserName;
    private EditText editUserSurname;
    private EditText editUserPhone;
    private EditText editUserEmail;
    private Button buttonSave;
    private Button buttonCancel;

    private DBUser dbUser;
    private User user;
    public int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppDefaultTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form_activity);

        initWidgets();

        buttonClickListeners();

        dbUser = new DBUser(this);
        user = new User();
        count = dbUser.getCount();
        
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.registration);
    }

    private boolean checkLengthForEditTexts() {
        if (editUserName.getText().length() == 0 ||
                editUserSurname.getText().length() == 0 ||
                editUserPhone.getText().length() == 0 ||
                editUserEmail.getText().length() == 0)
            return false;
        else
            return true;
    }

    private void buttonClickListeners() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checkLength = false;
                boolean checkEmail = false;

                if (checkLengthForEditTexts())
                    checkLength = true;
                else
                    Toast.makeText(getApplicationContext(), R.string.fields_not_filled, Toast.LENGTH_SHORT).show();

                if (isValidEmail(editUserEmail.getText()))
                    checkEmail = true;
                else
                    Toast.makeText(getApplicationContext(), R.string.is_not_email, Toast.LENGTH_SHORT).show();

                user.setName(editUserName.getText().toString());
                user.setSurname(editUserSurname.getText().toString());
                user.setPhone(editUserPhone.getText().toString());
                user.setEmail(editUserEmail.getText().toString());

                if (checkLength && checkEmail && dbUser.insertUser(user) != 0) {
                    Toast.makeText(getApplicationContext(), R.string.user_save, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), R.string.user_not_save, Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidgets() {
        /*
        Textviews
         */
        userName = (TextView) findViewById(R.id.userName);
        userSurname = (TextView) findViewById(R.id.userSurname);
        userPhone = (TextView) findViewById(R.id.userPhone);
        userEmail = (TextView) findViewById(R.id.userEmail);
        /*
        EditTexts
         */
        editUserName = (EditText) findViewById(R.id.editUserName);
        editUserSurname = (EditText) findViewById(R.id.editUserSurname);
        editUserPhone = (EditText) findViewById(R.id.editUserPhone);
        editUserEmail = (EditText) findViewById(R.id.editUserEmail);
        /*
        Buttons
         */
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        /*
        Settings
         */
        editUserPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
