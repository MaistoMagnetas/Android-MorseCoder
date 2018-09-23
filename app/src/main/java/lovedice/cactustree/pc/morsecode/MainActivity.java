package lovedice.cactustree.pc.morsecode;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Main class for adding all XML and action.
 */
public class MainActivity extends AppCompatActivity {

    private EditText morseCodeInput, morseCodeOutput;
    private Button btnToText, btnToLight, btnToVibration, btnToSound;

    private VibrationMorse vibrationMorse;
    private String inputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initXMLItems();

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
    }

    private void initXMLItems() {
        morseCodeInput = findViewById(R.id.morse_text_input);
        morseCodeOutput = findViewById(R.id.morse_text_output);
        btnToLight = findViewById(R.id.morse_button_light);
        btnToSound = findViewById(R.id.morse_button_sound);
        btnToText = findViewById(R.id.morse_button_text);
        btnToVibration = findViewById(R.id.morse_button_vibration);
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
