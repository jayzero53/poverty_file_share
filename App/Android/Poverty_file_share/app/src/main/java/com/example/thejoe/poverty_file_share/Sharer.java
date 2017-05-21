package com.example.thejoe.poverty_file_share;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;


/**
 * Created by The Joe on 21-May-17.
 */


public class Sharer {

    //inner class that only stores the file name and size
    private class Shared_file {

        final String file_name;
        final BigInteger file_size;

        Shared_file(String new_file, BigInteger bytes){
            this.file_name = new_file;
            this.file_size = bytes;
        }
    }

    private final String COMMA = ",";
    private final String COMMA_IN_WORD_REGEX ="\"(.*?)\",?";
    private final String csv_file;
    private ArrayList<Shared_file> shared_files = new ArrayList();

    public Sharer(String loaded_csv_file){
        // init func
        this.csv_file = loaded_csv_file;
        try {
            load_files();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load_files() throws IOException {

        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(this.csv_file));

        while ((line = br.readLine()) != null) {
            String [] csv_entry = line.split(COMMA);

            // for some reason, test csv ended with line comprised solely of a comma
            if (line.equals(","))
                continue;

            // line contains a string with a comma
            if (csv_entry.length > 2)
                csv_entry = line.split(COMMA_IN_WORD_REGEX);

            String filename = csv_entry[0];
            BigInteger file_size = new BigInteger(csv_entry[1]);

            Shared_file shared_file = new Shared_file(filename, file_size);
            this.shared_files.add(shared_file);
        }
    }













}