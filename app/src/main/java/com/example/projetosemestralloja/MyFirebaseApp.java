package com.example.projetosemestralloja;

import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseApp extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public void disableDatabase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        valid = 0;
    }
    private int valid;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
