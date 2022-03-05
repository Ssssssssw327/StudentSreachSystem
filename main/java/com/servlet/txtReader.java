package com.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

public class txtReader {
/**
 　　　* 获取txt文件内容, 并按行放入list中
 　　　*/
public static List<String> getFileContext( String path ) {
	FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    List<String> list = new ArrayList<String>();
    String str = "";
    try {
    InputStreamReader isr=new InputStreamReader(new FileInputStream(path),"utf-8");
    bufferedReader = new BufferedReader( isr );
    while( (str = bufferedReader.readLine()) != null ) {
        if( str.trim().length() >= 2 ) {
            list.add( str );
                                      }
    }
}
    catch ( Exception e ) {
    	e.printStackTrace();
    }
    finally {
    	try {
    		if (bufferedReader != null) {
    			bufferedReader.close();
    		}
    	} catch (Exception e2) {
    		e2.printStackTrace();
    	}

    	try {
    		if (fileReader != null) {
    			fileReader.close();
    		}
    	} catch (Exception e2) {
    		e2.printStackTrace();
    	}
    }
    return list;
	}
}