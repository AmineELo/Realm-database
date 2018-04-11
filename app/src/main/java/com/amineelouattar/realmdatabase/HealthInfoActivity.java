package com.amineelouattar.realmdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class HealthInfoActivity extends AppCompatActivity {

    private TextView genderT, weightT, ageT;
    private Realm realm;
    private RealmResults<HealthInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        genderT = findViewById(R.id.genderT);
        weightT = findViewById(R.id.weightT);
        ageT = findViewById(R.id.ageT);

        realm = Realm.getDefaultInstance();
        list = realm.where(HealthInfo.class).findAll();

        genderT.setText(list.get(0).getGender());
        ageT.setText(String.valueOf(list.get(0).getAge()));
        weightT.setText(String.valueOf(list.get(0).getWeight()));



    }
}
