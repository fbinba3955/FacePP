package com.tts.sjt.library.bean;

import java.util.List;

public class OcrIdCardBean {


    /**
     * image_id : D77lY5Q5ogmksH+OxJqt9w==
     * request_id : 1537511060,822eb20a-afd8-4b5a-b026-7860f42d5af5
     * cards : [{"name":"史健廷","gender":"男","id_card_number":"220203198802114518","birthday":"1988-02-11","race":"汉","address":"南京市玄武区北京东路63号","type":1,"side":"front"}]
     * time_used : 1737
     */

//    {
//        "image_id": "PYip1JoM2v5lVgufElgWRw==",
//            "request_id": "1537518060,83bfdc3f-054e-443d-a889-280eb3e82646",
//            "cards": [
//        {
//            "valid_date": "2010.07.15-2020.07.15",
//                "issued_by": "天长市公安局",
//                "type": 1,
//                "side": "back"
//        }
//    ],
//        "time_used": 583
//    }
    private String image_id;
    private String request_id;
    private int time_used;
    private List<CardsBean> cards;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public List<CardsBean> getCards() {
        return cards;
    }

    public void setCards(List<CardsBean> cards) {
        this.cards = cards;
    }

    public static class CardsBean {
        /**
         * name : 史健廷
         * gender : 男
         * id_card_number : 220203198802114518
         * birthday : 1988-02-11
         * race : 汉
         * address : 南京市玄武区北京东路63号
         * type : 1
         * side : front
         */
//            "valid_date": "2010.07.15-2020.07.15",
//                "issued_by": "天长市公安局",
//                "type": 1,
//                "side": "back"

        private String name;
        private String gender;
        private String id_card_number;
        private String birthday;
        private String race;
        private String address;
        private int type;
        private String side;
        private String valid_date;
        private String issued_by;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId_card_number() {
            return id_card_number;
        }

        public void setId_card_number(String id_card_number) {
            this.id_card_number = id_card_number;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public String getValid_date() {
            return valid_date;
        }

        public void setValid_date(String valid_date) {
            this.valid_date = valid_date;
        }

        public String getIssued_by() {
            return issued_by;
        }

        public void setIssued_by(String issued_by) {
            this.issued_by = issued_by;
        }
    }
}
