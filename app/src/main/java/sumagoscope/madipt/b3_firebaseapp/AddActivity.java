package sumagoscope.madipt.b3_firebaseapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btnAdd;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnAdd = findViewById(R.id.btnAdd);
        db = FirebaseFirestore.getInstance();

        btnAdd.setOnClickListener(v->{
            //add name & age in firestore database
            DocumentReference docRef = db.collection("students").document();
            String id = docRef.getId();
            String name = etName.getText().toString().trim();
            int age = Integer.parseInt(etAge.getText().toString().trim());

            db.collection("students")
                    .document(id)
                    .set(new Student(id, name, age))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AddActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}