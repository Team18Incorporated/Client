package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.RegisterCommand;
import edu.byu.cs.team18.tickettoride.Common.User;


public class ClientCommunicator {
    private static ClientCommunicator SINGLETON;
    private static Gson gson = new Gson();
    private static String SERVER_HOST = "67.205.155.130";
    private static String SERVER_PORT = "8080";
    private static String HTTP_POST = "POST";
    private static String HTTP_GET = "GET";
    private static String URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT;

    public Object send(String url, Object command, Class<?> klass){
        HttpURLConnection connection = openConnection("/"+url, HTTP_POST, true);
        sendToServerCommunicator(connection, command);
        Object result = null;
        result = returnResult(connection, klass);

        return result;
    }

    public static ClientCommunicator getSingleton(){
        if (SINGLETON == null) SINGLETON = new ClientCommunicator();
        return SINGLETON;
    }

    private HttpURLConnection openConnection(String contextIdentifier,
                                             String requestMethod,
                                             boolean sendingSomthingToServer)
    {
        HttpURLConnection result = null;
        try {
            URL url = new URL(URL_PREFIX + contextIdentifier);
            result = (HttpURLConnection)url.openConnection();
            result.setRequestMethod(requestMethod);
            result.setDoOutput(sendingSomthingToServer);
            result.connect();
        } catch (MalformedURLException e) {
            System.out.print(e.toString());
        } catch (IOException e) {
            System.out.print(e.toString());
        }

        return result;
    }
    private Object returnResult(HttpURLConnection connection, Class<?> klass) {
        Object result = null;
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //0 means the body reServerCommunicator.REGISTER_DESIGNATORsponse length == 0; it was empty
                if(connection.getContentLength() == 0) {
                    System.out.println("Response body was empty");
                } else if(connection.getContentLength() == -1) {
                    //-1 means the body was not empty but has an unknown about of information
                    /*JsonParser jsonParser = new JsonParser();
                    JsonObject jsonObject = jsonParser.parse(new BufferedReader(new InputStreamReader(connection.getInputStream()))).getAsJsonObject();
                    result = gson.fromJson(jsonObject, klass);*/
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    /******************************************/
                    result = gson.fromJson(inputStreamReader, klass);
                    /******************************************/
                    inputStreamReader.close();

                }
            } else {
                throw new Exception(String.format("http code %d",	connection.getResponseCode()));
            }
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            System.out.print(e.toString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return result;
    }

    private void sendToServerCommunicator(HttpURLConnection connection,
                                          Object objectToSend)
    {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(connection.getOutputStream());
            //Encoding in JSON
            gson.toJson(objectToSend, printWriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(printWriter != null) {
                printWriter.close();
            }
        }
    }

    public Object sendCmd(ICommand in){
        Object out = new CCAsyncTask().execute(in);
        return out;
    }

    private class CCAsyncTask extends AsyncTask<ICommand,Void,Object>{

        @Override
        protected Object doInBackground(ICommand... iCommands) {
            Object out = send(iCommands[0].getSuffix(),iCommands[0],AuthToken.class);;
            return out;
        }
    }

}
