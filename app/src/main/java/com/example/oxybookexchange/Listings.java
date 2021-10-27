package com.example.oxybookexchange;

public class Listings {

        private String title;
        private String course;
        private String semester;
        private String professor;
        private String quality;
        private String price;

    public Listings(String title, String course, String professor, String semester, String quality, String price) {
        this.title = title;
        this.course = course;
        this.semester = semester;
        this.professor = professor;
        this.quality = quality;
        this.price = price;
    }

    public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getProfessor() {
            return professor;
        }

        public void setProfessor(String professor) {
            this.professor = professor;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_listings);
//        //get intent
//        Intent intent = getIntent();
//
//    }

    }
}
