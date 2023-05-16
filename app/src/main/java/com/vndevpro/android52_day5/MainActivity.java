package com.vndevpro.android52_day5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Fragment";
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnReplace)
    Button btnReplace;
    @BindView(R.id.btnRemove)
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void addFrag() {
//        loginFragment = LoginFragment.newInstance("Hai", "Pham");
//        getSupportFragmentManager().beginTransaction().add(R.id.container, loginFragment).addToBackStack("ADD").commit();
//
//        Log.d(TAG, "addFrag: " + getSupportFragmentManager().getFragments().size());
    }

    private void initView() {
        ButterKnife.bind(this);

        btnAdd.setOnClickListener(this);
        btnReplace.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        boolean isLogin = getSharedPreferences("LOGIN", MODE_PRIVATE).getBoolean("LOGIN_STATE", false);
        if (isLogin) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).addToBackStack("ADD").commit();
        } else {
            loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, loginFragment).addToBackStack("ADD").commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addFrag();
                break;
            case R.id.btnReplace:
                replaceFrag();
                break;
            case R.id.btnRemove:
                removeFrag();
                break;
        }
    }

    private void removeFrag() {
//        getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();
//        getSupportFragmentManager().popBackStack();
    }

    LoginFragment loginFragment;

    private void replaceFrag() {
//        Log.d(TAG, "replaceFrag: " + getSupportFragmentManager().getFragments().size());
////        loginFragment = LoginFragment.newInstance();
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment).commit();
    }


}