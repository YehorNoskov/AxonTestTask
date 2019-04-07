package app.axon.test.api.error;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public final class ErrorHelper {

    public static String parseSimpleError(Throwable e) {
        String stringError = "";
        if (!(e instanceof HttpException)) {
            return stringError;
        }

        Response response = ((HttpException) e).response();
        if (response == null) {
            return stringError;
        }

        ResponseBody errorBody = response.errorBody();
        if (errorBody == null) {
            return stringError;
        }

        String rawJson;
        try {
            rawJson = errorBody.string();
        } catch (IOException e1) {
            return stringError;
        }

        Gson gson = new Gson();
        Error error = gson.fromJson(rawJson, Error.class);
        stringError = error.getError();

        return stringError;
    }
}
