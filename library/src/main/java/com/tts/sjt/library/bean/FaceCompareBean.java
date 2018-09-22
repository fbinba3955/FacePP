package com.tts.sjt.library.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaceCompareBean {


    /**
     * faces1 : [{"face_rectangle":{"width":185,"top":203,"left":382,"height":185},"face_token":"5cd7b0627b493dd96ed05796df96f3d3"}]
     * faces2 : [{"face_rectangle":{"width":140,"top":154,"left":289,"height":140},"face_token":"735b6fff24c9c5c791a7d38c754977b7"}]
     * time_used : 847
     * thresholds : {"1e-3":62.327,"1e-5":73.975,"1e-4":69.101}
     * confidence : 80.901
     * image_id2 : BZrcAtlEbQvO6wcb2Y4W3Q==
     * image_id1 : ZdWDIxZQmAQmxYLrwbB6cg==
     * request_id : 1537603690,c6377ff3-bf6d-4367-b349-72dc93084bad
     */

    private int time_used;
    private ThresholdsBean thresholds;
    private double confidence;
    private String image_id2;
    private String image_id1;
    private String request_id;
    private List<Faces1Bean> faces1;
    private List<Faces2Bean> faces2;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public ThresholdsBean getThresholds() {
        return thresholds;
    }

    public void setThresholds(ThresholdsBean thresholds) {
        this.thresholds = thresholds;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getImage_id2() {
        return image_id2;
    }

    public void setImage_id2(String image_id2) {
        this.image_id2 = image_id2;
    }

    public String getImage_id1() {
        return image_id1;
    }

    public void setImage_id1(String image_id1) {
        this.image_id1 = image_id1;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<Faces1Bean> getFaces1() {
        return faces1;
    }

    public void setFaces1(List<Faces1Bean> faces1) {
        this.faces1 = faces1;
    }

    public List<Faces2Bean> getFaces2() {
        return faces2;
    }

    public void setFaces2(List<Faces2Bean> faces2) {
        this.faces2 = faces2;
    }

    public static class ThresholdsBean {
        /**
         * 1e-3 : 62.327
         * 1e-5 : 73.975
         * 1e-4 : 69.101
         */

        @SerializedName("1e-3")
        private double _$1e3;
        @SerializedName("1e-5")
        private double _$1e5;
        @SerializedName("1e-4")
        private double _$1e4;

        public double get_$1e3() {
            return _$1e3;
        }

        public void set_$1e3(double _$1e3) {
            this._$1e3 = _$1e3;
        }

        public double get_$1e5() {
            return _$1e5;
        }

        public void set_$1e5(double _$1e5) {
            this._$1e5 = _$1e5;
        }

        public double get_$1e4() {
            return _$1e4;
        }

        public void set_$1e4(double _$1e4) {
            this._$1e4 = _$1e4;
        }
    }

    public static class Faces1Bean {
        /**
         * face_rectangle : {"width":185,"top":203,"left":382,"height":185}
         * face_token : 5cd7b0627b493dd96ed05796df96f3d3
         */

        private FaceDetect83Bean.FacesBean.FaceRectangleBean face_rectangle;
        private String face_token;

        public FaceDetect83Bean.FacesBean.FaceRectangleBean getFace_rectangle() {
            return face_rectangle;
        }

        public void setFace_rectangle(FaceDetect83Bean.FacesBean.FaceRectangleBean face_rectangle) {
            this.face_rectangle = face_rectangle;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }


    }

    public static class Faces2Bean {
        /**
         * face_rectangle : {"width":140,"top":154,"left":289,"height":140}
         * face_token : 735b6fff24c9c5c791a7d38c754977b7
         */

        private FaceDetect83Bean.FacesBean.FaceRectangleBean face_rectangle;
        private String face_token;

        public FaceDetect83Bean.FacesBean.FaceRectangleBean getFace_rectangle() {
            return face_rectangle;
        }

        public void setFace_rectangle(FaceDetect83Bean.FacesBean.FaceRectangleBean face_rectangle) {
            this.face_rectangle = face_rectangle;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

    }
}
