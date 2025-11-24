package com.realtimeeditor.api;

import com.realtimeeditor.dto.SignUpRequest;
import java.io.IOException;
import retrofit2.Response;

public class SignUpApiService {

    public boolean callSignUpApi(SignUpRequest request) throws IOException {
        Response<Void> response = ApiClient.signUpApi().signUp(request).execute();

        return response.isSuccessful();
    }
}
