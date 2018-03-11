package pet.delete.com;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pet.delete.com.deletepet.R;

public class MainActivity extends AppCompatActivity {

//http://chikuo.tw/android-x-firebase-02-%E8%B3%87%E6%96%99%E5%AD%98%E5%8F%96/
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TextView txtpetname;
    TextView txtdate;
    String putPetname;
    String putPetdate;


    //從FIREBASE 取出資料
    public static  ArrayList<PetData> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtpetname = (TextView)findViewById(R.id.petName);
        txtdate = (TextView)findViewById(R.id.date);
        putPetname = txtpetname.getText().toString();
        putPetdate = txtdate.getText().toString();

    }
    //上傳firebase
    public void buttonSave(View v)
    {

        String key=myRef.child("notes").push().getKey();
        PetData user = new PetData (putPetname,putPetdate);
        //這行是跟據路徑  送到FIREBASE 上
        myRef.child("notes").child(key).setValue(user);



        Toast.makeText(MainActivity.this,"已執行",Toast.LENGTH_SHORT).show();
    }

    //刪除firebase資料
    public void buttonDelete(View v)
    {
        //String key=myRef.child("notes").push().getKey();
       // myRef.child("notes").child(key).child("2018-02-28").removeValue();

        list=new ArrayList<>();
        //在這個子節點 notes 設定監聽 只要資料有變就會執行 ValueEventListener 這物件
        //這叫CALLBACK
        myRef.child("notes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //  dataSnapshot ，取得FIREBASE 裡 notes 節點裡全部內容
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    //FIREBASE 裡取出來的資料
                    PetData note = noteDataSnapshot.getValue(PetData.class);
                    //OK  這邊可以接
                    list.add(note);
                    Log.d("PDAlistsize-->", "" + list.size());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Toast.makeText(MainActivity.this,"已執行",Toast.LENGTH_SHORT).show();
    }
}
