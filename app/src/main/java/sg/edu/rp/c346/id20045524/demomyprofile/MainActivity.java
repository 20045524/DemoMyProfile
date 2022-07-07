package sg.edu.rp.c346.id20045524.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etName, etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.rgGender);
    }

    //save when app closes aka autosave
    @Override
    protected void onPause() {
        super.onPause();
        // Step 1a: Get user input from EditText and store in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        String gender;
        if (rgGender.getCheckedRadioButtonId() == R.id.rbMale){
            gender = "Male";
        } else {
            gender = "Female";
        }
        // Step 2: Obtain the SharedPreferences instance
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        // Step 3: Create a SharedPreferences Editor by calling edit()
        SharedPreferences.Editor prefEdit = prefs.edit();
        // step 4: Set a key-value pair in the editor
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putString("gender", gender);
        // Step 5: Call commit() to save the changes made to the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Step 1: Obtain the SharedPreferences instance
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        // Step 2: Retrieve the saved data from the SharedPreferences
        // with a default value if no matching data
        String strName = prefs.getString("name", "Default name");
        float gpa = prefs.getFloat("gpa", 0);
        String gender = prefs.getString("gender", "Male");
        // Step 3: Update the UI element with the retrieved data
        etName.setText(strName);
        etGPA.setText(gpa + "");
        if (gender.equals("Male") ){
            rgGender.check(R.id.rbMale);
        } else if ( gender.equals("Female") ){
            rgGender.check(R.id.rbFemale);
        }
    }


}