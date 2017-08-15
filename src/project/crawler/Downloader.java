package project.crawler;

import java.net.URL;


import java.io.InputStream;
import java.io.BufferedInputStream;

import java.io.IOException;
import java.net.MalformedURLException;


public class Downloader{


    private InputStream stream;
    private BufferedInputStream buf;


    public Downloader(){

        stream = null;
        buf = null;

    }

    private void closeStream(){


        try{

            stream.close();
            buf.close();
        }catch(IOException e){

        }

    }


    private static URL download(String url){

        URL urlObj = null;

        try{
            urlObj = new URL(url);

        }catch(MalformedURLException e){
            System.out.println("Could not download the url.");
        }

        return urlObj;


    }

    public String getContent(String url_path){


        String content = "";


        URL url = download(url_path);


        if(url == null)
            return content;

        try{


            stream = url.openStream();
            buf = new BufferedInputStream(stream);
            StringBuilder stringBuilder = new StringBuilder();


            while(true){

                int data = buf.read();

                if(data == -1)
                    break; 

                stringBuilder.append((char)data);

            }

            content =  stringBuilder.toString();


        }catch(IOException e){
            System.out.println("Error loading the buffer when downloading the url");

        } finally {
            closeStream();
        }
        
        return content;

    }


}