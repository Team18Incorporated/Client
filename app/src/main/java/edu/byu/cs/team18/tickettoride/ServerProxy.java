package com.example.abram.phase1main;

import com.example.abram.phase1main.Commands.Command;
import com.example.abram.phase1main.Commands.CreateCommand;
import com.example.abram.phase1main.Commands.JoinCommand;
import com.example.abram.phase1main.Commands.RegisterCommand;
import com.example.abram.phase1main.Commands.StartCommand;
import com.example.abram.phase1main.Commands.UpdateLobbyCommand;
import com.example.abram.phase1main.ModelClasses.AuthToken;
import com.example.abram.phase1main.ModelClasses.GameInfo;
import com.example.abram.phase1main.ModelClasses.GameList;
import com.example.abram.phase1main.ModelClasses.User;
import com.example.abram.phase1main.Results.CommandResult;
import com.example.abram.phase1main.Commands.LoginCommand;
import com.example.abram.phase1main.Results.JoinResult;
import com.example.abram.phase1main.Results.LoginResult;
import com.example.abram.phase1main.Results.RegisterResult;
import com.example.abram.phase1main.Results.UpdateResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by abram on 10/7/2017.
 */

public class ServerProxy {

        public static String port = "8080";
        public static String IPAddress = "192.168.1.7";
        private static ServerProxy serverProxy = null;

        private ServerProxy(){}

        public static ServerProxy getServerProxy()
        {
            if(serverProxy == null)
            {
                serverProxy = new ServerProxy();
            }
            return serverProxy;
        }

        public LoginResult login(LoginCommand loginCommand, String suffix)
        {
            try {
                URL url = new URL("http://" + IPAddress + ":" + port + "/" + suffix);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.addRequestProperty("Content-Type", "application/json");
                connection.connect();

                String request = "{\"username\": \"" + loginCommand.getUsername()+ "\"," +
                        "\"password\": \"" + loginCommand.getPassword() + "\"" + "}";

                OutputStream body = connection.getOutputStream();
                writeString(body, request);
                body.close();


                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    Gson gson = new Gson();
                    LoginResult command_result = gson.fromJson(isr, LoginResult.class);
                    if(!command_result.getToken().equals("null"))
                    {
                        //If the login was successful, the server proxy calls the client communicator in order
                        //to create the new user.
                        User user = new User(loginCommand.getUsername(), new AuthToken(command_result.getToken()));
                        ClientCommunicator.getClientcommunicator().updateUser(user);
                    }
                    return command_result;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            return new LoginResult("null","Server Error");
        }

        public RegisterResult register(RegisterCommand registerCommand, String suffix)
        {
            try {
                URL url = new URL("http://" + IPAddress + ":" + port + "/" + suffix);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.addRequestProperty("Content-Type", "application/json");
                connection.connect();

                String request = "{\"username\": \"" + registerCommand.getUsername()+ "\"," +
                        "\"password\": \"" + registerCommand.getPassword() + "\"" + "}";

                OutputStream body = connection.getOutputStream();
                writeString(body, request);
                body.close();


                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    Gson gson = new Gson();
                    RegisterResult command_result = gson.fromJson(isr, RegisterResult.class);
                    if(!command_result.getToken().equals("null"))
                    {
                        //If the registration was successful, the server proxy calls the client communicator in order
                        //to create the new user.
                        User user = new User(registerCommand.getUsername(), new AuthToken(command_result.getToken()));
                        ClientCommunicator.getClientcommunicator().updateUser(user);
                    }
                    return command_result;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            return new RegisterResult("null","Server Error");
        }

    public JoinResult join(JoinCommand joinCommand, String suffix)
    {
        try {
            URL url = new URL("http://" + IPAddress + ":" + port + "/" + suffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.connect();

            String request = "{\"username\": \"" + joinCommand.getUsername()+ "\"," +
                    "\"gameID\": \"" + joinCommand.getGameID() + "\"," +
                    "\"token\": \"" + joinCommand.getToken() + "\"}";

            OutputStream body = connection.getOutputStream();
            writeString(body, request);
            body.close();


            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();
                JoinResult command_result = gson.fromJson(isr, JoinResult.class);
                if(!command_result.getGameInfo().equals(null))
                {
                    //If the registration was successful, the server proxy calls the client communicator in order
                    //to create the new user
                    ClientCommunicator.getClientcommunicator().updateGame(command_result.getGameInfo());
                }
                return command_result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return new JoinResult(null,"Server Error");
    }

    public JoinResult create(CreateCommand createCommand, String suffix)
    {
        try {
            URL url = new URL("http://" + IPAddress + ":" + port + "/" + suffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.connect();

            String request = "{\"username\": \"" + createCommand.getUsername()+ "\"," +
                    "\"token\": \"" + createCommand.getToken() + "\"}";

            OutputStream body = connection.getOutputStream();
            writeString(body, request);
            body.close();


            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();
                JoinResult command_result = gson.fromJson(isr, JoinResult.class);
                if(!command_result.getGameInfo().equals(null))
                {
                    //If the registration was successful, the server proxy calls the client communicator in order
                    //to create the new user

                    //MUST CREATE NEW GAME, NOT SURE HOW TO DO THIS.
                    ClientCommunicator.getClientcommunicator().updateGame(command_result.getGameInfo());
                }
                return command_result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return new JoinResult(null,"Server Error");
    }

    public JoinResult start(StartCommand startCommand, String suffix)
    {
        try {
            URL url = new URL("http://" + IPAddress + ":" + port + "/" + suffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.connect();

            String request = "{\"gameID\": \"" + startCommand.getGameID()+ "\"," +
                    "\"token\": \"" + startCommand.getGameID() + "\"}";

            OutputStream body = connection.getOutputStream();
            writeString(body, request);
            body.close();


            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();
                JoinResult command_result = gson.fromJson(isr, JoinResult.class);
                if(!command_result.getGameInfo().equals(null))
                {
                    //If the registration was successful, the server proxy calls the client communicator in order
                    //to create the new user

                    //Find game that matches gameID and update that one.
                    GameInfo info = command_result.getGameInfo();
                    ClientCommunicator.getClientcommunicator().updateGame(info);
                }
                return command_result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return new JoinResult(null,"Server Error");
    }

    public UpdateResult updateLobby(UpdateLobbyCommand updateLobbyCommand, String suffix)
    {
        try {
            URL url = new URL("http://" + IPAddress + ":" + port + "/" + suffix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.connect();

            String request = "{\"username\": \"" + updateLobbyCommand.getUsername()+ "\"," +
                    "\"token\": \"" + updateLobbyCommand.getToken() + "\"}";

            OutputStream body = connection.getOutputStream();
            writeString(body, request);
            body.close();


            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();
                UpdateResult command_result = gson.fromJson(isr, UpdateResult.class);
                if(!command_result.getGameList().equals(null))
                {
                    //If the registration was successful, the server proxy calls the client communicator in order
                    //to create the new user
                    GameList list = command_result.getGameList();
                    ClientCommunicator.getClientcommunicator().updateCurrentGamesList(list);
                    ClientCommunicator.getClientcommunicator().updateJoinGameList(list);
                }
                return command_result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return new UpdateResult(null,"Server Error");
    }










        public static void writeString(OutputStream os, String s) throws IOException {
            OutputStreamWriter writer = new OutputStreamWriter(os);
            writer.write(s);
            writer.flush();
        }

        public static String readString(InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            InputStreamReader sr = new InputStreamReader(is);
            char[] buf = new char[1024];
            int len;
            while ((len = sr.read(buf)) > 0) {
                sb.append(buf, 0, len);
            }
            return sb.toString();
        }
}
