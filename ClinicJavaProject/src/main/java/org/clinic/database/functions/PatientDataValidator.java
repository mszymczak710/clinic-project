package org.clinic.database.functions;

import org.clinic.database.tables.Patient;

import java.time.LocalDate;

public class PatientDataValidator {

    public boolean isPeselValid(Patient patient) {
        String PESEL = patient.getPesel();

        if (!PESEL.matches("^\\d{11}$")) return false;

        return true;
    }

    public boolean isZipCodeValid(Patient patient) {
        String ZipCode = patient.getZipCode();

        if (!ZipCode.matches("^[0-9]{2}-?[0-9]{3}$")) return false;

        return true;
    }

    public boolean isPhoneNumberValid(Patient patient) {
        String phoneNumber = patient.getPhoneNumber();

        if (!phoneNumber.matches("[1-9]\\d{2}\\d{3}\\d{3}")) return false;

        return true;
    }

    public boolean isEmailAddressValid(Patient patient) {
        String emailAddress = patient.getEmailAddress();

        if (!emailAddress.matches("^.+@.+\\..+$")) return false;

        return true;
    }

    public boolean checkPeselWithDateOfBirth(Patient patient) {
        String PESEL = patient.getPesel();
        String dateOfBirth = patient.getDateOfBirth().toString();

        if (!isPeselValid(patient) && !dateOfBirth.matches("^\\d{4}-\\d{2}-\\d{2}$")) return false;

        int yearFromPESEL = Integer.valueOf(PESEL.substring(0, 2));
        int monthFromPESEL = Integer.valueOf(PESEL.substring(2, 4));
        int dayFromPESEL = Integer.valueOf(PESEL.substring(4, 6));

        int yearFromDateOfBirth = Integer.valueOf(dateOfBirth.substring(0, 4));
        int monthFromDateOfBirth = Integer.valueOf(dateOfBirth.substring(5, 7));
        int dayFromDateOfBirth = Integer.valueOf(dateOfBirth.substring(8, 10));

        if (yearFromDateOfBirth < 1800 || yearFromDateOfBirth > 2299) return false;
        if (yearFromDateOfBirth >= 1800 && yearFromDateOfBirth <= 1899) monthFromDateOfBirth += 80;
        else if (yearFromDateOfBirth >= 1900 && yearFromDateOfBirth <= 1999) monthFromDateOfBirth += 0;
        else if (yearFromDateOfBirth >= 2000 && yearFromDateOfBirth <= 2099) monthFromDateOfBirth += 20;
        else if (yearFromDateOfBirth >= 2100 && yearFromDateOfBirth <= 2199) monthFromDateOfBirth += 40;
        else if (yearFromDateOfBirth >= 2200 && yearFromDateOfBirth <= 2299) monthFromDateOfBirth += 60;

        if (yearFromDateOfBirth % 100 != yearFromPESEL) return false;
        if (monthFromDateOfBirth != monthFromPESEL) return false;
        if (dayFromDateOfBirth != dayFromPESEL) return false;

        return true;
    }

    public String getGenderFromPesel(Patient patient) {
        if(isPeselValid(patient)) {
            String PESEL = patient.getPesel();

            int genderFromPesel = Integer.valueOf(PESEL.charAt(9));
            if ( (genderFromPesel % 2 == 1))
                return "Mezczyzna";
            else
                return "Kobieta";
        } else
            return "Niepoprawny PESEL!";
    }

    public long getAgeFromDateOfBirth(Patient patient) {
        long years;
        String dateOfBirth = patient.getDateOfBirth().toString();
        String today = LocalDate.now().toString();

        long dob = Integer.parseInt(dateOfBirth);
        long now = Integer.parseInt(today);

        years = now - dob;

        return years;
    }
}