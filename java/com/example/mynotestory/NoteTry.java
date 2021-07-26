package com.example.mynotestory;

public class NoteTry {

        public String nametitle;
        public  String datename;
        public String descriptionname;
        public  int id;

        public NoteTry(int id,String nametitle, String datename, String descriptionname) {
            this.nametitle = nametitle;
            this.datename = datename;
            this.descriptionname = descriptionname;
            this.id = id;
        }

        public NoteTry() {
        }

        public NoteTry(String nametitle, String datename, String descriptionname) {
            this.nametitle = nametitle;
            this.datename = datename;
            this.descriptionname = descriptionname;
        }



    public String getNametitle() {
            return nametitle;
        }

        public void setNametitle(String nametitle) {
            this.nametitle = nametitle;
        }

        public String getDatename() {
            return datename;
        }

        public void setDatename(String datename) {
            this.datename = datename;
        }

        public String getDescriptionname() {
            return descriptionname;
        }

        public void setDescriptionname(String descriptionname) {
            this.descriptionname = descriptionname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }


