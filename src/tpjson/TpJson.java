/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjson;

import java.io.IOException;
import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author tabaha
 */
public class TpJson {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        String json = FileReader.loadFileIntoString("catalog.json", "utf-8");
        JSONArray cds = JSONArray.fromObject(json);
        // Build the album list to add in the order
        JSONArray albums = new JSONArray();
        double total = 0.0;
        for(int i = 0; i < cds.size(); i++) {
            JSONObject album = cds.getJSONObject(i);
            if(album.getDouble("price") < 10.0) {
                total += album.getDouble("price");
                albums.add(album);
            }
        }
        // Format the price
        DecimalFormat format = new DecimalFormat();
        format.setMinimumFractionDigits(2);
        String totalStr = format.format(total);
        // Build the order
        JSONObject order = new JSONObject();
        order.accumulate("id", "1321033823");
        order.accumulate("total", totalStr);
        order.accumulate("date", "11/11/2011");
        order.accumulate("validated", true);
        order.accumulate("albums", albums);
        System.out.println(order);
        /*
        JSONArray albums = JSONArray.fromObject(json);
       // System.out.println("Il y a " + albums.size() + " CD(s) dans le catalogue.");
        System.out.println("Albums parus depuis 1990:");
        int cpt = 0;
        for(int i = 0; i < albums.size(); i++) {
            JSONObject album = albums.getJSONObject(i);
            if(album.getInt("year") >= 1990) {
                cpt += 1;
                System.out.println(" * " + album.getString("title"));
            }
        }
        System.out.println("Il y a " + albums.size() + " CD(s) dans le catalogue dont " + cpt + " paru(s) depuis 1990.");
                */
    }
    
}
