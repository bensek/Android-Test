package com.example.airlineschedule.view.ui;

import java.io.Serializable;

public class Trip implements Serializable {
        public String getNewOrigin() {
            return newOrigin;
        }

        public void setNewOrigin(String newOrigin) {
            this.newOrigin = newOrigin;
        }

        String newOrigin;

        public String getNewDest() {
            return newDest;
        }

        public void setNewDest(String newDest) {
            this.newDest = newDest;
        }

        String newDest;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        String date;
}
