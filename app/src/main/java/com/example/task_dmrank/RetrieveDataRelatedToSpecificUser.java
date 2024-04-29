
package com.example.task_dmrank;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.task_dmrank.databinding.ActivityRetrieveDataRelatedToSpecificUserBinding;

public class RetrieveDataRelatedToSpecificUser extends AppCompatActivity {
    private ActivityRetrieveDataRelatedToSpecificUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRetrieveDataRelatedToSpecificUserBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ModelClass modelClass = (ModelClass) getIntent().getSerializableExtra("values");
        binding.firstnameRetrieved.setText(modelClass.getFirstName());
        binding.lastNameRetrieved.setText(modelClass.getLastName());
        binding.ageRetrieved.setText(modelClass.getAge());
        binding.cityRetrieved.setText(modelClass.getCity());
        Glide.with(getApplicationContext()).load(modelClass.url).centerCrop().placeholder(R.drawable.baseline_person_24).into(binding.retrieveUserImage);
    }
}