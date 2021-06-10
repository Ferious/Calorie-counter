package statistics;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.Month;

public class ProgressRecord {
    private LocalDate date;
    private Double value;

    public ProgressRecord(JSONObject record, String property) {
        String dateRecord = (String) record.get("date");
        this.date = makeDate(dateRecord);
        Double w;
        try {
            w = (Double) record.get(property);
        } catch (Exception e) {
            w = ((Long) record.get(property)).doubleValue();
        }
        this.value = w;
    }

    public ProgressRecord(String date, double value){
        this.date = makeDate(date);
        this.value = value;
    }

    private LocalDate makeDate(String strDate){
        int posOfSpace = strDate.indexOf(' ');
        int index = ( posOfSpace > 0)? posOfSpace: strDate.length();
        String modified = strDate.substring(0,index);
        return Statistics.toLocalDate(modified);
    }

    public Month getMonth(){
        return date.getMonth();
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

}
