package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.pia.androidlibrary.AdnroidMainActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static android.content.ContentValues.TAG;

class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override                         public void initialize(AbstractGoogleClientRequest<?>
                                                                                         abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        context = params[0];


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return  null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
//        Create Intent to launch JokeFactory Activity
            Intent intent = new Intent(context, AdnroidMainActivity.class);
            // Put the string in the envelope
            intent.putExtra("joke", result);
            context.startActivity(intent);
    }
}
