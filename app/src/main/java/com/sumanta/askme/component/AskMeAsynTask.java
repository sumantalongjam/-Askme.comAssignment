package com.sumanta.askme.component;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sumanta.askme.entities.DataEntity;
import com.sumanta.askme.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by sumanta on 5/3/16.
 */
public class AskMeAsynTask extends AsyncTask<Void, Void, ArrayList<DataEntity>> {

    private Context context;
    private TaskListener taskListener;

    public AskMeAsynTask(Context context, TaskListener taskListener) {
        this.context = context;
        this.taskListener = taskListener;
    }
    @Override
    protected void onPreExecute() {
        taskListener.onPreExecute();
    }
    @Override
    protected ArrayList<DataEntity> doInBackground(Void... args) {
        try {
            String string = null;
            StringBuilder stringBuffer = new StringBuilder();
            InputStream inputStream = context.getResources().openRawResource(R.raw.data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if (inputStream != null) {
                while ((string = reader.readLine()) != null) {
                    stringBuffer.append(string);
                }
            }
            inputStream.close();
            Type listType = new TypeToken<ArrayList<DataEntity>>() {}.getType();
            return new Gson().fromJson(stringBuffer.toString().trim(), listType);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<DataEntity> resultList) {
        if(resultList!=null)
            taskListener.onPostExecute(resultList);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
    public interface TaskListener {
        void onPreExecute();
        void onPostExecute(ArrayList<DataEntity> resultList);
    }
}
