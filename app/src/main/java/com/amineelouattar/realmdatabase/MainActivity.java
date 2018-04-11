package com.amineelouattar.realmdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private EditText mGender, mWeight, mAge;
    private Realm realm;
    private Button add;
    private RealmResults<HealthInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Realm database
        Realm.init(this);

        mGender = findViewById(R.id.gender);
        mWeight = findViewById(R.id.weight);
        mAge = findViewById(R.id.age);
        add = findViewById(R.id.add);

        //get an instance of Realm database
        realm = Realm.getDefaultInstance();

        //get all database lines of HealthInfo class/table
        list = realm.where(HealthInfo.class).findAll();

        //if it contains a line then set it to the Edit texts
        if(list.size() >= 1){
            mGender.setText(list.get(0).getGender());
            mWeight.setText(String.valueOf(list.get(0).getWeight()));
            mAge.setText(String.valueOf(list.get(0).getAge()));
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persistData();
            }
        });



    }

    private void persistData(){
        String gender = mGender.getText().toString();
        int weight = Integer.valueOf(mWeight.getText().toString());
        int age = Integer.valueOf(mAge.getText().toString());

        final HealthInfo healthInfo = new HealthInfo(gender, weight, age);

        //test if the database has a record of healthinfo
        if(list.size() == 0){
            //if not then add a new record

            realm.beginTransaction();
            realm.copyToRealm(healthInfo);
            realm.commitTransaction();
            changeActivity();
        }else{
            //if yes then update the existing record
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    HealthInfo health = realm.where(HealthInfo.class).findFirst();
                    health.setAge(healthInfo.getAge());
                    health.setGender(healthInfo.getGender());
                    health.setWeight(healthInfo.getWeight());
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    //On successfully updating Realm database do something
                    Log.d("realm", "Success");
                    changeActivity();
                }
            });
        }

    }

    private void changeActivity(){
        Intent intent = new Intent(MainActivity.this, HealthInfoActivity.class);
        startActivity(intent);
    }


}
