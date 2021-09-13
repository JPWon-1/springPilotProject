package com.start.pilotproject.service.history;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private DatabaseReference mDatabase;
    
    public void getAll(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase db = mDatabase.getDatabase();
        // DatabaseReference reference = db.getReference("april/1");
        DatabaseReference reference = db.getReference();
        reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object value = snapshot.getValue();
                System.out.println("");
            }
            
            @Override
            public void onCancelled(DatabaseError error) {
                String message = error.getMessage();
                System.out.println(message);
                
            }
        });

        System.out.println("test");
    }
}
