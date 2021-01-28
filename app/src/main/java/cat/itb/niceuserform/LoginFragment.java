package cat.itb.niceuserform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    TextView title;
    TextInputLayout loginInput;
    TextInputEditText loginEditText;
    TextInputLayout passwordInput;
    TextInputEditText passwordEditText;
    Button login;
    Button register;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login_fragment,container,false);
        title = v.findViewById(R.id.LoginTitle);
        loginInput = v.findViewById(R.id.input_layout_login_username);
        loginEditText = v.findViewById(R.id.input_text_login_username);
        passwordInput = v.findViewById(R.id.input_layout_login_password);
        passwordEditText = v.findViewById(R.id.input_text_login_password);
        login = v.findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginEditText.getText().toString().isEmpty()){
                    loginInput.setError("Empty username");
                } else{
                    loginInput.setError("");
                    if (passwordEditText.getText().toString().isEmpty()){
                        passwordInput.setError("Insert a valid password");
                    } else if(passwordEditText.getText().toString().length()<8) {
                        passwordInput.setError("Insert a valid password");
                    } else {
                        passwordInput.setError("");
                        NavDirections loginToWelcome = LoginFragmentDirections.loginToWelcome();
                        Navigation.findNavController(v).navigate(loginToWelcome);
                    }
                }
            }
        });
        register = v.findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections loginToRegister = LoginFragmentDirections.loginToRegister();
                Navigation.findNavController(v).navigate(loginToRegister);
            }
        });
        return v;
    }
}