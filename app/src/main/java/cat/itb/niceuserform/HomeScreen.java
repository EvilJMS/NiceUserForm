package cat.itb.niceuserform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends Fragment {
    TextView title;
    Button login;
    Button register;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home_screen,container,false);
        title = v.findViewById(R.id.titleHomeScreen);
        login = v.findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections homeToLogin = HomeScreenDirections.homeToLogin();
                Navigation.findNavController(v).navigate(homeToLogin);
            }
        });
        register = v.findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections homeToRegister = HomeScreenDirections.homeToRegister();
                Navigation.findNavController(v).navigate(homeToRegister);
            }
        });
        return v;
    }
}