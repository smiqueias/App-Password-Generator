package com.imagine.app_passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.security.SecureRandom;


public class TelaPrincipal extends AppCompatActivity {

    TextView tv_passwordResult;
    CheckBox cb_lowerCase, cb_include_numbers, cb_upperCase;
    Button bt_generatePassword;
    RadioGroup radiogroup;
    RadioButton radioButton;
    StringBuilder concatenatingLetters;
    private final String alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String numbers = "0123456789";
    private final String concatenateNumbersLetters = alphabetUpperCase + alphabetUpperCase.toLowerCase() + numbers;
    char[] alphabet = alphabetUpperCase.toCharArray();
    char[] alphabetLowerCase = alphabetUpperCase.toLowerCase().toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        tv_passwordResult = findViewById(R.id.tv_passwordResult);
        cb_include_numbers = findViewById(R.id.cb_include_numbers);
        cb_lowerCase = findViewById(R.id.cb_lowerCase);
        cb_upperCase = findViewById(R.id.cb_upperCase);
        bt_generatePassword = findViewById(R.id.bt_generatePassword);
        radiogroup = findViewById(R.id.radiogroup);


        bt_generatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!cb_upperCase.isChecked() && !cb_include_numbers.isChecked() && !cb_lowerCase.isChecked()) {
                    Toast.makeText(TelaPrincipal.this, "Selecione um filtro",
                            Toast.LENGTH_LONG).show();
                }

                else if (cb_upperCase.isChecked() && cb_lowerCase.isChecked()) {

                    switch (buttonSelectedAsInteger()) {

                        case 5:
                            tv_passwordResult.setText(generateUpperAndLowerCaseLetters(5));
                            break;
                        case 10:
                            tv_passwordResult.setText(generateUpperAndLowerCaseLetters(10));
                            break;
                        case 15:
                            tv_passwordResult.setText(generateUpperAndLowerCaseLetters(15));
                            break;
                        case 20:
                            tv_passwordResult.setText(generateUpperAndLowerCaseLetters(20));
                            break;
                    }
                } else if (cb_upperCase.isChecked()) {

                    switch (buttonSelectedAsInteger()) {
                        case 5:
                            tv_passwordResult.setText(generateRandomUppercasePassword(5));
                            break;

                        case 10:
                            tv_passwordResult.setText(generateRandomUppercasePassword(10));
                            break;
                        case 15:
                            tv_passwordResult.setText(generateRandomUppercasePassword(15));
                            break;

                        case 20:
                            tv_passwordResult.setText(generateRandomUppercasePassword(20));
                            break;
                    }
                }

                else if (cb_lowerCase.isChecked()) {
                    switch (buttonSelectedAsInteger()) {

                        case 5:
                            tv_passwordResult.setText(generateRandomUppercasePassword(5)
                                    .toString()
                                    .toLowerCase());

                            break;

                        case 10:
                            tv_passwordResult.setText(generateRandomUppercasePassword(10)
                                    .toString()
                                    .toLowerCase());
                            break;
                        case 15:
                            tv_passwordResult.setText(generateRandomUppercasePassword(15)
                                    .toString()
                                    .toLowerCase());
                            break;

                        case 20:
                            tv_passwordResult.setText(generateRandomUppercasePassword(20)
                                    .toString()
                                    .toLowerCase());
                            break;
                    }
                }

                else if(cb_include_numbers.isChecked()) {

                    switch (buttonSelectedAsInteger()) {

                        case 5:
                                tv_passwordResult.setText(generateRandomNumbersToPassword(5));
                            break;

                    }
                }
            }
        });
    }


    //FUNÇÕES DE APOIO

    /*Transforma a String alphabetUpperCase em um array de caracteres
      e de acordo com o tamanho indicado pelo usuário é retornado o password
     */
    public CharSequence generateRandomUppercasePassword(int passwordlength) {

        concatenatingLetters = new StringBuilder();


        for (int i = 0; i < passwordlength; i++) {

            int random = new SecureRandom().nextInt(alphabet.length);

            concatenatingLetters.append(alphabet[random]);
        }
        return concatenatingLetters;
    }

    //Identifica qual tamanho de senha o usuário escolheu e retorna o mesmo como inteiro
    public int buttonSelectedAsInteger() {

        int radioId = radiogroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        return Integer.parseInt(radioButton.getText().toString());
    }

    //Pega os dois arrays de caracteres e faz concatenação desses dois.
    public char[] concatenatingUpperAndLowerCase(char[] arrayLettersUpper, char[] arrayLettersLower) {

        char[] newArrayConcatenated = new char[arrayLettersLower.length + arrayLettersUpper.length];

        int sentinela = 0;

        for (int i = 0; i < arrayLettersUpper.length; i++) {
            newArrayConcatenated[sentinela++] = arrayLettersLower[i];
        }

        for (int i = 0; i < arrayLettersUpper.length; i++) {
            newArrayConcatenated[sentinela++] = arrayLettersUpper[i];
        }

        return newArrayConcatenated;
    }

    //Percorre o array concatenado para retornar letras maiúsculas e minúsculas
    public CharSequence generateUpperAndLowerCaseLetters(int passwordlength) {

        concatenatingLetters = new StringBuilder();

        char[] sequence = concatenatingUpperAndLowerCase(alphabet, alphabetLowerCase);

        for (int i = 0; i < passwordlength; i++) {

            int random = new SecureRandom().nextInt(alphabet.length + alphabetLowerCase.length);

            concatenatingLetters.append(sequence[random]);
        }
        return concatenatingLetters;
    }

    public StringBuilder generateRandomNumbersToPassword(int passwordlength) {

        concatenatingLetters = new StringBuilder();

        for (int i = 0; i < passwordlength; i++) {
            int random = new SecureRandom().nextInt(10);

            concatenatingLetters.append(random);
        }

        return  concatenatingLetters;
    }
}
