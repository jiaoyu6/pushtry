package com.com.weather;

class Weather {
    private String city; //城市
    private String date; //日期
    private String text_day; //白天天气描述
    private String text_night; //夜晚天气描述
    private String high; // 最高温度
    private String low; //最低温度
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getText_day() {
        return text_day;
    }
    public void setText_day(String text_day) {
        this.text_day = text_day;
    }
    public String getText_night() {
        return text_night;
    }
    public void setText_night(String text_night) {
        this.text_night = text_night;
    }
    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }
    @Override
    public String toString() {
        return "Weather [city=" + city + ", date=" + date + ", text_day="
                + text_day + ", text_night=" + text_night + ", high=" + high
                + ", low=" + low + "]";
    }

}

