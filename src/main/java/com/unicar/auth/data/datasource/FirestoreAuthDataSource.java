package com.unicar.auth.data.datasource;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.inject.Inject;
import com.unicar.auth.data.model.User;
import com.unicar.util.log.Logger;

import java.io.InterruptedIOException;
import java.util.Map;

public class FirestoreAuthDataSource implements AuthDataSource {

    private final Firestore firestore;

    @Inject
    public FirestoreAuthDataSource(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            DocumentReference docRef = firestore.collection("users")
                    .whereEqualTo("email", email)
                    .get()
                    .get()
                    .getDocuments()
                    .get(0)
                    .getReference();
            DocumentSnapshot documentSnapshot = docRef.get().get();

            final String userId = documentSnapshot.getId();
            final String emailResponse = documentSnapshot.getString("email");
            final String passwordResponse = documentSnapshot.getString("password");


            if (emailResponse == null || passwordResponse == null) {
                return null;
            }
            return new User(emailResponse, userId, passwordResponse);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void createUser(String email, String hashPassword, String name, String phone, String ra) throws InterruptedIOException {
        try {
            final int documentsWithThisEmail = firestore.collection("users")
                    .whereEqualTo("email", email)
                    .get()
                    .get()
                    .getDocuments()
                    .size();

            if (documentsWithThisEmail == 0) {
                firestore.collection("users").add(Map.of("email", email, "password", hashPassword));
                String userId = firestore.collection("users")
                        .whereEqualTo("email", email)
                        .get()
                        .get()
                        .getDocuments()
                        .get(0)
                        .getId();
                firestore.collection("profile").document(userId).set(Map.of("name", name, "phone", phone, "ra", ra));
            } else {
                throw new InterruptedIOException("Usuário já existe.");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            throw new InterruptedIOException("Erro ao salvar usuário.");
        }
    }
}
