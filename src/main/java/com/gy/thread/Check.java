package com.gy.thread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Check {

    public static void main(String[] args) throws IOException {

        BufferedReader r = new BufferedReader(new FileReader("D:\\source\\project\\thread_Test\\src\\main\\file\\file1" ));
        List<String> list = new ArrayList<>();
        String line = "";
        while ((line = r.readLine()) != null){
            String[] key = line.split("，");
            String word = key[1];
            if(!list.contains(word)){
                list.add(word);
            }else{
                System.err.println("重票了");
            }
        }



    }
}
