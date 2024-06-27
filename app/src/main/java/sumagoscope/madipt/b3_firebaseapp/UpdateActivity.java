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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btnUpdate;
    String id;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnUpdate = findViewById(R.id.btnUpdate);
        id = getIntent().getStringExtra("id");
        getData();

        btnUpdate.setOnClickListener(v->{
            updateData(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()));
        });
    }

    private void updateData(String name, int age) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("age", age);
        db.collection("students").document(id).update(data)
                .addOnCompleteListener(task->{
                   if(task.isSuccessful()){
                       Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
                       finish();
                   }
                });
    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("students").document(id).get()
                .addOnCompleteListener(task->{
                    if(task.isSuccessful()){
                        Student student = task.getResult().toObject(Student.class);
                        etName.setText(student.getName());
                        etAge.setText(String.valueOf(student.getAge()));
                    }
                });
    }
}