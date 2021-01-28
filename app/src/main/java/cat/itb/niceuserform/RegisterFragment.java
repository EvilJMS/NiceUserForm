package cat.itb.niceuserform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    TextView title;
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout repeatPass;
    TextInputLayout name;
    TextInputLayout surname;
    TextInputLayout date;
    TextInputLayout pronouns;
    TextInputLayout email;
    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    TextInputEditText repeatPassEditText;
    TextInputEditText nameEditText;
    TextInputEditText surnameEditText;
    TextInputEditText dateEditText;
    AutoCompleteTextView pronounsEditText;
    TextInputEditText emailEditText;
    CheckBox checkBox;
    Button confirm;
    String[] pronounslist = {"He/him","She/her","They/them"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment,container,false);
        title = v.findViewById(R.id.registerTitle);
        username = v.findViewById(R.id.input_layout_username);
        usernameEditText = v.findViewById(R.id.input_text_username);
        password = v.findViewById(R.id.input_layout_password);
        repeatPass = v.findViewById(R.id.input_layout_password_repeat);
        passwordEditText = v.findViewById(R.id.input_text_password);
        repeatPassEditText = v.findViewById(R.id.input_text_password_repeat);
        name = v.findViewById(R.id.input_layout_name);
        surname = v.findViewById(R.id.input_layout_surname);
        date = v.findViewById(R.id.input_layout_date);
        pronouns = v.findViewById(R.id.input_layout_pronouns);
        email = v.findViewById(R.id.input_layout_email);
        nameEditText = v.findViewById(R.id.input_text_name);
        surnameEditText=v.findViewById(R.id.input_text_surname);
        dateEditText = v.findViewById(R.id.input_text_date);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        pronounsEditText = v.findViewById(R.id.pronounsAutocomplete);
        emailEditText = v.findViewById(R.id.input_text_email);
        checkBox= v.findViewById(R.id.checkConditions);
        confirm = v.findViewById(R.id.registerButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerUser()){
                    NavDirections RegisterWelcome = RegisterFragmentDirections.registerToWelcome();
                    Navigation.findNavController(v).navigate(RegisterWelcome);
                }
            }
        });
        arrayAdapter=new ArrayAdapter<>(getContext(),R.layout.list_item,pronounslist);
        pronounsEditText.setAdapter(arrayAdapter);
        pronounsEditText.setThreshold(1);
        return v;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                dateEditText.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public boolean registerUser(){
        if (usernameEditText.getText().toString().isEmpty()){
            username.setError("Empty username");
            return false;
        } else{
            username.setError("");
            if (passwordEditText.getText().toString().isEmpty()){
                password.setError("Insert a valid password");
                return false;
            } else if(passwordEditText.getText().toString().length()<8) {
                password.setError("Insert a valid password");
                return false;
            } else {
                password.setError("");
                if (!passwordEditText.getText().toString().equals(repeatPassEditText.getText().toString())){
                    repeatPass.setError("the passwords are different");
                    return false;
                } else {
                    repeatPass.setError("");
                    if (emailEditText.getText().toString().isEmpty()){
                        email.setError("Required field");
                        return false;
                    }else {
                        email.setError("");
                        if (nameEditText.getText().toString().isEmpty()){
                            name.setError("Required field");
                            return false;
                        } else {
                            name.setError("");
                            if (surnameEditText.getText().toString().isEmpty()){
                                surname.setError("Required field");
                                return false;
                            } else {
                                surname.setError("");
                                if (dateEditText.getText().toString().isEmpty()){
                                    date.setError("Required field");
                                    return false;
                                } else {
                                    date.setError("");
                                    if (pronounsEditText.getText().toString().isEmpty()){
                                        pronouns.setError("Required field");
                                        return false;
                                    } else {
                                        pronouns.setError("");
                                        if (!checkBox.isChecked()){
                                            checkBox.setError("Accept this!");
                                            return false;
                                        } else {
                                            checkBox.setError("");
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}