package com.itz.gst.helpers.common.calc;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


public class DateTimeCalc {

    private static final int SECOND = 60;
    @Value("${spring.date.time.dateTimeFormat}")
    public final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final String UCLO_DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    @Value("${spring.date.time.dateTimeFormatInFile}")
    public final String DATE_TIME_FORMAT_FILE = "yyyy-MM-dd_hh-mm-ss";

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {

        return dateToConvert.toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
    }

    public long calcStrDurInMin(String stTime, String edTime) throws ParseException {
        Date startTime = new SimpleDateFormat(DATE_TIME_FORMAT).parse(stTime);
        Date endTime = new SimpleDateFormat(DATE_TIME_FORMAT).parse(edTime);
        return calcDurInMin(startTime, endTime);
    }

    public long calcDurInMin(Date stTime, Date edTime) {
        LocalDateTime startTime = convertToLocalDateTimeViaInstant(stTime);
        LocalDateTime endTime = convertToLocalDateTimeViaInstant(edTime);
        return (Duration.between(startTime, endTime).getSeconds()) / SECOND;
    }


    public Long convDiffTimeAsMin(Date stDt, Date edDt) {
        long diff = edDt.getTime() - stDt.getTime();
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((edDt.getTime() - stDt.getTime()) / (1000 * 60 * 60 * 24));
        return diffMinutes;
    }

    public long calcStrDurCurAsMin(String chkTime) throws ParseException {
        Date checkTime = new SimpleDateFormat(DATE_TIME_FORMAT).parse(chkTime);
        Date now = new Date();
        return convDiffTimeAsMin(checkTime, now);
    }

    public long calcDurCurAsMin(Date checkTime) {
        Date now = new Date();
        return convDiffTimeAsMin(checkTime, now);
    }

    public String getTodayDateTimeForFileName() {
        return new SimpleDateFormat(DATE_TIME_FORMAT_FILE).format(new Date());
    }

    public String getUcloTodayDate() {
        String pattern = "dd/MM/yyyy";
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public String getUcloTodayDateTime() {
        return new SimpleDateFormat(UCLO_DATE_TIME_FORMAT).format(new Date());
    }

    public String getTodayDateTime() {
        Date dateTest = new Date();
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
    }

    public String getTodayDate() {
        String pattern = DATE_TIME_FORMAT.split(" ")[0];
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public long getCurrentTime() {
        return new Date().getTime();
    }

    public String getUtcTime() {
        Instant instant = Instant.now();
        return instant.toString();
    }

    public Timestamp getUtcTimestamp() {
        Instant instant = Instant.now();
        return Timestamp.from(instant);
    }

    public String convertTimeStampToString(Timestamp getDate){
        Date date = new Date(getDate.getTime());
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
    }
}