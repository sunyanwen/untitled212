package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
        istr.put("{",17);
        istr.put("}",18);
        istr.put(";",19);


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
        int flagw;
        int curnow = 0;
        int length = 0;
        Vector<String> temp = new Vector<>();
        Vector<String> temp1 = new Vector<>();
        Vector<String> temp2 = new Vector<>();
        //startwith toffset
        //处理int double 等后接变量名 判断注释
        //不在范围内并且有空格的左值认为是用户标识符 右值认为是常量


        for(String tmp:line.split(" ")){

           for(String tmp2:tmp.split("[()]"))
            {
                System.err.println(tmp2);
            }
            for (Map.Entry<String, Integer> entry : istr.entrySet()) {
                if (entry.getKey().equals(tmp)) {
                    System.out.println("(" + entry.getValue() + "," + entry.getKey() + ")");
                }
            }

            /*
            判断是否为保留关键字  19的情况需要单独处理  需要换容器放置数据
             */
            if(tmp.contains(";")){
                int part1= tmp.indexOf(";"); //预留用于分割;为双串
                tmp=tmp.replace(';','\0'); //先去除;
            }
            //if.tmp.contains('')
            if (tmp.contains("=")){   //处理赋值情况
                int leftvaluetemp = tmp.indexOf('=');
                String left = tmp.substring(0,leftvaluetemp);
                left=left.trim();
                System.out.println("(" + 50 + "," + left + ")");
                System.out.println("("+13+",=)");
                String right = tmp.substring(leftvaluetemp+1);
                if(right.indexOf(")")>=1){
                    right=right.substring(0,right.indexOf(')'));
                }
                right=right.trim();
                System.out.println("(" + 51 + "," + right + ")");
            }

            //if(tmp.)

            //System.out.println(tmp);
        }

/*
        for (flagw = 0; flagw != -1; curnow += length) {
            for (Map.Entry<String, Integer> entry : istr.entrySet()) {
                if (line.startsWith(entry.getKey())) {
                    System.out.println("(" + entry.getValue() + "," + entry.getKey() + ")");
                    line = line.substring(entry.getKey().length());
                    //保留字判断完成 trim进行下次判断
                    if (line.startsWith(" ")) {
                        line=line.trim();
                        System.err.println("trimed");
                    }
                    System.err.println(line);
                } else {
                    line.
                }
                line.trim();




            }
*/

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
