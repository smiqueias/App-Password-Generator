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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TelaPrincipal extends AppCompatActivity {

    TextView tv_passwordResult;
    CheckBox cb_lowerCase, cb_include_numbers, cb_upperCase;
    Button bt_generatePassword;
    RadioGroup radiogroup;
    RadioButton radioButton;
    StringBuilder concatenatingLetters;
    CharSequence[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


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

                else if (cb_upperCase.isChecked()) {

                    switch (buttonSelectedAsInteger()) {
                        case 5:
                            tv_passwordResult.setText(letterGenerator(5));
                            break;

                        case 10:
                            tv_passwordResult.setText(letterGenerator(10));
                            break;
                        case 15:
                            tv_passwordResult.setText(letterGenerator(15));
                            break;

                        case 20:
                            tv_passwordResult.setText(letterGenerator(20));
                            break;
                    }
                }

                else if (cb_lowerCase.isChecked()) {
                    switch (buttonSelectedAsInteger()) {

                        case 5:
                            tv_passwordResult.setText(letterGenerator(5)
                                    .toString().toLowerCase());
                            break;

                        case 10:
                            tv_passwordResult.setText(letterGenerator(10)
                                    .toString().toLowerCase());
                            break;
                        case 15:
                            tv_passwordResult.setText(letterGenerator(15)
                                    .toString().toLowerCase());
                            break;

                        case 20:
                            tv_passwordResult.setText(letterGenerator(20)
                                    .toString().toLowerCase());
                            break;
                    }
                }

                else if (cb_upperCase.isChecked() && cb_lowerCase.isChecked()) {

                    tv_passwordResult.setText(scrambledLetters(5));
                }

            }
        });
    }

    //FUNÇÕES DE APOIO


    //Retorna uma palavra aleatória do array alphabet
    public CharSequence letterAlphabet() {

        CharSequence returnedLetter = "";

            int alfabetoIndice = new Random().nextInt(26);
            returnedLetter = alphabet[alfabetoIndice];

        return  returnedLetter;
    }

    //Identifica qual tamanho de senha o usuário escolheu e retorna o mesmo como inteiro
    public int buttonSelectedAsInteger() {

        int radioId = radiogroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        return Integer.parseInt(radioButton.getText().toString());
    }

    /*De acordo com o tamanho escolhido pelo usuário a função retorna
    letras aleatórias do array alphabet e junta tudo
     */
    public CharSequence letterGenerator(int passwordLength) {

        concatenatingLetters = new StringBuilder();

        for(int i = 0; i < passwordLength; i++) {
            letterAlphabet();
            concatenatingLetters.append(letterAlphabet());
        }
        return concatenatingLetters;
    }

    public CharSequence scrambledLetters(int passwordLength) {

        List<CharSequence> lettersUpperAndLowerCase = new ArrayList<>();

            concatenatingLetters = new StringBuilder();


        for (int i = 0; i < passwordLength; i++) {

            int random = new Random().nextInt(10000);
            int indexRandom = new Random().nextInt(27);

            if (random % 2 == 0) {
                lettersUpperAndLowerCase.add(alphabet[indexRandom]);
                concatenatingLetters.append(lettersUpperAndLowerCase);
            }
            else {
                lettersUpperAndLowerCase.add(alphabet[indexRandom].toString().toLowerCase());
                concatenatingLetters.append(lettersUpperAndLowerCase);
            }

        }
            return concatenatingLetters;
        }
    }