package id.zainalfahrudin.pengingatsholat.data.model;

/**
 * Created by zainal on 11/7/16 - 2:40 PM.
 * Zainal Fahrudin
 * fnzainal@gmail.com
 */
public class WaktuSholatHarianModel {

 /**
     * status : success
     * data : {"method":"Umm al-Qura, Makkah","year":"2016","month":"10","day":"27","address":"Semarang","latitude":-7.0051453,"longitude":110.4381254,"timezone":"7"}
     * result : {"fajr":"03:59","sunrise":"05:12","dhuhr":"11:22","asr":"14:35","sunset":"17:32","maghrib":"17:32","isha":"19:02"}
     */

    private String status;
    /**
     * method : Umm al-Qura, Makkah
     * year : 2016
     * month : 10
     * day : 27
     * address : Semarang
     * latitude : -7.0051453
     * longitude : 110.4381254
     * timezone : 7
     */

    private DataBean data;
    /**
     * fajr : 03:59
     * sunrise : 05:12
     * dhuhr : 11:22
     * asr : 14:35
     * sunset : 17:32
     * maghrib : 17:32
     * isha : 19:02
     */

    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean {
        private String method;
        private String year;
        private String month;
        private String day;
        private String address;
        private double latitude;
        private double longitude;
        private String timezone;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }
    }

    public static class ResultBean {
        private String fajr;
        private String sunrise;
        private String dhuhr;
        private String asr;
        private String sunset;
        private String maghrib;
        private String isha;

        public String getFajr() {
            return fajr;
        }

        public void setFajr(String fajr) {
            this.fajr = fajr;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getDhuhr() {
            return dhuhr;
        }

        public void setDhuhr(String dhuhr) {
            this.dhuhr = dhuhr;
        }

        public String getAsr() {
            return asr;
        }

        public void setAsr(String asr) {
            this.asr = asr;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMaghrib() {
            return maghrib;
        }

        public void setMaghrib(String maghrib) {
            this.maghrib = maghrib;
        }

        public String getIsha() {
            return isha;
        }

        public void setIsha(String isha) {
            this.isha = isha;
        }
    }
}
