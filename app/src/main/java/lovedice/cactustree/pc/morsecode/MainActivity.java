package lovedice.cactustree.pc.morsecode;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Main class for adding all XML and action.
 */
public class MainActivity extends AppCompatActivity {

    private EditText morseCodeInput, morseCodeOutput;
    private Button btnToText, btnToLight, btnToVibration, btnToSound;
    private Button btnMorseSpace, btnMorseDot, btnMorseDash, btnMorseWordEnd, btnMorseDel;
    private CheckBox btnMorseSwitchMode;
    private boolean isMorseModeEnabled = false;

    private VibrationMorse vibrationMorse;
    private String inputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initXMLItems();
        changeStatusOfButtons(isMorseModeEnabled); //Disable buttons on app load

        btnToText.setOnClickListener(new View.OnClickListener() { //Morse to text
            @Override
            public void onClick(View v) {
                inputString = morseCodeInput.getText().toString().toLowerCase();
                if (isStringValid(inputString)) {
                    changeTextAfterInput();
                    hideKeyboardThenClickedOnButton();
                }
            }
        });

        btnToVibration.setOnClickListener(new View.OnClickListener() { //Vibration morse
            @Override
            public void onClick(View v) {
                inputString = morseCodeInput.getText().toString().toLowerCase();
                if (isStringValid(inputString)) {
                    changeTextAfterInput();
                    hideKeyboardThenClickedOnButton();
                    try {
                        vibrationMorse = new VibrationMorse(inputString);
                        long[] patternForVibrating = vibrationMorse.getPatternForVibrating();
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        if (vibrator != null) {
                            vibrator.vibrate(patternForVibrating, -1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        btnToLight.setOnClickListener(new View.OnClickListener() { //Light morse
            @Override
            public void onClick(View v) {
                inputString = morseCodeInput.getText().toString().toLowerCase();
                if (isStringValid(inputString)) {
                    changeTextAfterInput();
                    hideKeyboardThenClickedOnButton();
                    if (deviceHasCameraFlash()) {
                        try {
                            new FlashMorse(inputString);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            generateAllertMessage();
                        }
                    }
                }
            }
        });

        btnToSound.setOnClickListener(new View.OnClickListener() { //Sound Morse
            @Override
            public void onClick(View v) {
                inputString = morseCodeInput.getText().toString().toLowerCase();
                if (isStringValid(inputString)) {
                    changeTextAfterInput();
                    hideKeyboardThenClickedOnButton();
                    new SoundMorse(inputString);
                }
            }
        });

        btnMorseDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextOnMorseButtonClick("-");
            }
        });

        btnMorseDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextOnMorseButtonClick("•");

            }
        });

        btnMorseSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextOnMorseButtonClick(" ");

            }
        });

        btnMorseWordEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextOnMorseButtonClick("/");

            }
        });

        btnMorseSwitchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeStatusOfMode();
                clearTextsOnModeChanged();
                changeStatusOfButtons(isMorseModeEnabled);

            }
        });

        btnMorseDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringBefore = morseCodeOutput.getText().toString();
                int stringSize = stringBefore.length();
                if (stringSize > 0) {
                    morseCodeOutput.setText(stringBefore.substring(0, stringBefore.length() - 1));
                }
            }
        });
    }

    /**
     * Changes status of morse or text input
     */
    private void changeStatusOfMode() {
        isMorseModeEnabled ^= true;
    }

    /**
     * Clears input/ output text fields data after mode changed.
     */
    private void clearTextsOnModeChanged()
    {
        morseCodeOutput.setText("");
        morseCodeInput.setText("");
    }

    /**
     * Enables buttons if morse mode is turned on
     * @param status status to enable or dissable buttons.
     */
    private void changeStatusOfButtons(boolean status) {
        if (isMorseModeEnabled) {
            btnMorseDel.setEnabled(status);
            btnMorseWordEnd.setEnabled(status);
            btnMorseDot.setEnabled(status);
            btnMorseDash.setEnabled(status);
            btnMorseSpace.setEnabled(status);
            morseCodeInput.setInputType(InputType.TYPE_NULL);
        } else {
            btnMorseDel.setEnabled(status);
            btnMorseWordEnd.setEnabled(status);
            btnMorseDot.setEnabled(status);
            btnMorseDash.setEnabled(status);
            btnMorseSpace.setEnabled(status);
            morseCodeInput.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    /**
     * Sets text to morse input text area.
     * @param type type => dot, dash, space etc.
     */
    private void setTextOnMorseButtonClick(String type) {
        String stringBefore = morseCodeOutput.getText().toString();
        morseCodeOutput.setText(stringBefore + type);
    }

    private void initXMLItems() {
        //Text
        morseCodeInput = findViewById(R.id.morse_text_input);
        morseCodeOutput = findViewById(R.id.morse_text_output);
        btnToLight = findViewById(R.id.morse_button_light);
        btnToSound = findViewById(R.id.morse_button_sound);
        btnToText = findViewById(R.id.morse_button_text);
        btnToVibration = findViewById(R.id.morse_button_vibration);
        morseCodeOutput.setInputType(InputType.TYPE_NULL);
        //Morse
        btnMorseDash = findViewById(R.id.morse_button_dash);
        btnMorseDot = findViewById(R.id.morse_button_dot);
        btnMorseSpace = findViewById(R.id.morse_button_space);
        btnMorseWordEnd = findViewById(R.id.morse_button_wordend);
        btnMorseDel = findViewById(R.id.morse_button_remove);
        btnMorseSwitchMode = findViewById(R.id.morse_button_switchmode);
    }

    private boolean isStringNotEmpty(String inputString) {
        return inputString.length() > 0 && inputString != null;
    }

    private boolean isStringNotJustSpaces(String inputString) {
        return inputString.trim().length() > 0;
    }

    private boolean isStringValid(String inputString) {
        return isStringNotEmpty(inputString) && isStringNotJustSpaces(inputString);
    }

    /**
     * Hides keyboard then clicked anywhere on options.
     */
    private void hideKeyboardThenClickedOnButton() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * Changes text field output after any button click.
     */
    private void changeTextAfterInput() {
        TextMorse textMorse = new TextMorse(inputString);
        String convertedString = textMorse.getConvertedText();
        morseCodeOutput.setText(convertedString);
    }


    //CAMERA FLASH MORSE PART

    /**
     * Generates alert message if device does not have a camera.
     */
    private void generateAllertMessage() {
        AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                .create();
        alert.setTitle("Error");
        alert.setMessage("Sorry, your device doesn't support flash light or permission was not granted!");
        alert.setCancelable(true);
        alert.show();
    }

    /**
     * Checks if device has camera flash
     *
     * @return boolean
     */
    private boolean deviceHasCameraFlash() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
}
