package com.vndevpro.android52_day5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements IRegisterListener {

    private static final String TAG = "Fragment" + LoginFragment.class.getName();

    private String p1;
    private String p2;

    @BindView(R.id.tv_SignUp)
    TextView tv_SignUp;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("P1", param1);
        args.putString("P2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            p1 = getArguments().getString("P1");
            p2 = getArguments().getString("P2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.layout_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        tv_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignUp();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });

    }

    private void gotoLogin() {
        Gson gson = new Gson();
        String data = gson.toJson(userInfo, UserInfo.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("LOGIN_STATE", true);
        editor.putString("USER_INFO", data);
        editor.apply();

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setUserInfo(userInfo);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).addToBackStack("LOGIN").commit();
    }

    private void gotoSignUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        signUpFragment.setRegisterListener(this);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, signUpFragment).addToBackStack("SIGNUP").commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    private UserInfo userInfo;

    @Override
    public void onRegisterSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
        edtPhoneNumber.setText(userInfo.getPhone());
    }

    @Override
    public void onRegisterCancel() {

    }
}