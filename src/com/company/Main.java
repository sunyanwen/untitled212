package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    File input;
    Map<String,Integer> istr = new HashMap<>();
    Map<String,Integer> ustr = new HashMap<>();
    boolean init(String fname){
        input= new File(fname);
        if (input.isFile()){
            System.out.println("Source Open Success");
        } else {
            System.out.println("Can't open "+fname);
            return false;
        }
        istr.put("double",1);
        istr.put("int",2);
        istr.put("long",3);
        istr.put("char",4);
        istr.put("float",5);
        istr.put("short",6);
        istr.put("for",7);
        istr.put("if",8);
        istr.put("else",9);
        istr.put("while",10);
        istr.put("(",11);
        istr.put(")",12);
        istr.put("=",13);
        istr.put("<",14);
        istr.put(">",15);
        istr.put("!",16);


        FileInputStream infile = null;
        try {
            infile = new FileInputStream(fname);

        InputStreamReader inReader = new InputStreamReader(infile, "UTF-8");
        BufferedReader bufReader = new BufferedReader(inReader);
        String line = null;
        int i = 1;
        while((line = bufReader.readLine()) != null){
            //readline to parse
            parser(line);
            i++;
        }
        bufReader.close();
        inReader.close();
        infile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    void parser(String line) {
        line.trim();


        //判断开头是否为关键字
        for (Map.Entry<String, Integer> entry : istr.entrySet()) {
            if (line.startsWith(entry.getKey())){
                System.out.println("("+entry.getValue()+","+entry.getKey()+")");
            } else{}
        }

    }




    public static void main(String[] args) {
	// write your code here
        if(args.length==0){
            System.out.println("Usage: input xxx.c as argument");
            System.exit(1);
        } else {
            Main test = new Main();
            if(test.init(args[0])){

            }else{
                System.exit(1);
            }
        }


    }
}


/*
标识符需要解析出来 统一放表里 (map)   注意\
    关键字也放map里
            文档读入  解析变量

判断是否为关键字   如果不是  判断是不是()或{}  如果不是  判断是不是用户定义的标识符



auto double int struct break else long switch
case enum register typedef char extern return union
const float short unsigned continue for signed void
default goto sizeof volatile do if while static


double int long char float short for if else while    unsigned signed
*/
