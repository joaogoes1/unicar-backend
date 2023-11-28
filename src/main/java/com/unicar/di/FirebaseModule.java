package com.unicar.di;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseModule extends AbstractModule {

    private static final String FIREBASE_PROJECT_ID = "unicar-maps-2ea13";
    private static final String FIREBASE_AUTHORIZATION_FILE_PATH = "/Users/joaogoes/Downloads/unicar-maps-2ea13-firebase-adminsdk-do6dg-d7879e9c58.json";

    @Provides
    static Firestore provideFirestore() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(FIREBASE_AUTHORIZATION_FILE_PATH);
        return FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId(FIREBASE_PROJECT_ID)
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }
}
