/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author geovanny
 */
public class HttpConnection 
{
    private String url = "https://fcm.googleapis.com/fcm/send";
    private String key = "AIzaSyDsrnh6Or7epuA0RgKBrzBpem5l6Y1WSWM";
    private String tipo = "application/json";
    private int READ=20000;//20 segundos;
    private int CONNECT=10000;
    
    public HttpConnection()
    {
        
    }
    
    public Response postJson(String json) throws IOException 
    {
        URL u =  new URL(url);       

        HttpURLConnection con = (HttpURLConnection) u.openConnection();

        con.setRequestMethod("POST");//Tipo de metodo de la conexión
        con.setDoInput(true);//habilita el response code
        con.setDoOutput(true);
        con.setReadTimeout(READ);//fijando el Tiempo de espera de lectura
        con.setConnectTimeout(CONNECT);//fijando el Tiempo de espera de la conexión
        con.setRequestProperty("Content-Type",tipo);
        con.setRequestProperty("Authorization", "key = "+key);
        con.connect(); 
        
        OutputStream stream= con.getOutputStream();
        stream.write(json.getBytes());

        InputStream in = con.getInputStream();
        Response response =  new Response(streamToString(in), con.getResponseCode());
        return response;
        
    }
    
    private String streamToString(InputStream in) throws IOException
    {
        StringBuilder rta = new StringBuilder();
        String rline = "";
        BufferedReader br= new BufferedReader(new InputStreamReader(in));

        try
        {
            while ((rline= br.readLine())!= null)//leemos linea a linea hasta llegar al final
            {
                rta.append(rline);//vamos adjuntando en el stringbuilder;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return rta.toString();//retornamos la informacion del stringbuilder en un string
    }
}
