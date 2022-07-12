package org.przychodnia.DBconfig;

public class Doctor {
    private int doctor_id;
    private String first_name;
    private String last_name;

    public Doctor(int doctor_id, String first_name, String last_name) {
        this.doctor_id = doctor_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
