package ca.uottawa.leyaoli.seg2105_final_project;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.*;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountSetting extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private CircleImageView imageAv ;
    private TextView disname;
    private TextView numChore;
    private Button changeImageBt;
    private Button changeNameBt;
    private static final int AVATAR_NUM = 1;
    private StorageReference imageStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String curren_uid = currentUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(curren_uid);
        disname = (TextView) findViewById(R.id.SettingName);
        imageAv = (CircleImageView) findViewById(R.id.settingAvatar);
        numChore = (TextView) findViewById(R.id.somethingElse);
        changeImageBt = (Button) findViewById(R.id.changeImage);
        imageStorage = FirebaseStorage.getInstance().getReference();
        changeNameBt = (Button)findViewById(R.id.changeName);
        // ===========================================================


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                //String image = dataSnapshot.child("image").getValue().toString();
                String points = dataSnapshot.child("points").getValue().toString();
               disname.setText("User Name :" +name);
               numChore.setText("Current Points"+ points);


                  // Picasso.with(AccountSetting.this).load(image).placeholder(R.drawable.avatar).into(imageAv);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        changeImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Avatra"),AVATAR_NUM);


            }
        });
        changeNameBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSetting.this,ChangeName.class);
                startActivityForResult(intent,0);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AVATAR_NUM && resultCode == RESULT_OK){

            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(this);
            //Toast.makeText(AccountSetting.this,imageUri,Toast.LENGTH_LONG).show();
        }else if (requestCode == 0 && resultCode == 0){
            final String name =  data.getStringExtra("res");
            disname.setText(name);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String image = dataSnapshot.child("image").getValue().toString();
                    String  points = (String) dataSnapshot.child("points").getValue();
                    User upInfo = new User(name,currentUser.getEmail(),image,points);
                    databaseReference.setValue(upInfo);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

//================================================================================================crop imgage libary

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                String curren_user_id = currentUser.getUid();
                StorageReference filepath = imageStorage.child("profile_image").child( curren_user_id +".jpg");
                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){

                            String download_link = task.getResult().getDownloadUrl().toString();
                            databaseReference.child("image").setValue(download_link).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // test
                                }
                            });
                        }
                    }
                });
                // complete listener

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }



}
