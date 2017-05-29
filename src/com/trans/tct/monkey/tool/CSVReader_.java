package com.trans.tct.monkey.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVReader_ {


    String file_name;

    static ArrayList<HashMap<String, String>> CSVData;

    public static ArrayList<HashMap<String, String>> ReadCSV() throws IOException {
    	
    	File file = new File("C:\\id_en.csv");

        InputStream is = new FileInputStream(file);

        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader Br = new BufferedReader(isr);

        String line;

        String cvsSplitBy = ",";

        Br.readLine();

        CSVData = new ArrayList<HashMap<String, String>>();

        while ((line = Br.readLine()) != null) {

            String[] row = line.split(cvsSplitBy);

            HashMap<String, String> hm = new HashMap<String, String>();
            
            hm.put("row0", row[0]);
            
            StringBuffer sb = new StringBuffer();

            for (int i = 1; i <= row.length - 1; i++) {
            
            	sb.append(row[i]);
            
            }
            
            if(!"".equals(sb)){
            	hm.put("row1", sb.toString());
            }

            CSVData.add(hm);

        }

        return CSVData;

    }

}
