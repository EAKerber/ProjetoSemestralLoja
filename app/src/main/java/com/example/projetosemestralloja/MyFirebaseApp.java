package com.example.projetosemestralloja;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseApp extends android.app.Application{
    //private static FirebaseAuth fbAuth;
    private static FirebaseDatabase fbStore;

    public static FirebaseDatabase getFirebaseDatabaseInstance() {
        if (fbStore == null) {
            fbStore = FirebaseDatabase.getInstance();
        }
        return fbStore;
    }

    private int valid;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
