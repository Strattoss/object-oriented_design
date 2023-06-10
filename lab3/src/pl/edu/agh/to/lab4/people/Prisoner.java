package pl.edu.agh.to.lab4.people;

import java.util.Calendar;

public class Prisoner extends Suspect {
    private final int judgementYear;

    private final int sentenceDuration;

    private final String pesel;
    private final int age;

    public Prisoner(String firstName, String lastName, String pesel, int judgementYear, int sentenceDuration) {
        super(firstName, lastName);
        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.sentenceDuration = sentenceDuration;
        this.age = getCurrentYear() - getYearOfBirth();
    }

    public String getPesel() {
        return pesel;
    }

    public boolean isJailedNow() {
        return judgementYear + sentenceDuration >= getCurrentYear();
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    public boolean canBeAccused() {
        return !isJailedNow() && getAge() > 18;
    }

    @Override
    public int getAge() {
        return age;
    }

    private int getYearOfBirth() {
        if (pesel.length() != 11) {
            throw new IllegalArgumentException("Invalid PESEL number");
        }

        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        if (month % 20 > 12 || day > 31) {
            throw new IllegalArgumentException("Invalid PESEL number");
        }

        int century;
        switch (month / 20) {
            case 0:
            case 1:
                century = 1900;
                break;
            case 2:
            case 3:
                century = 2000;
                break;
            case 4:
            case 5:
                century = 2100;
                break;
            case 6:
            case 7:
                century = 2200;
                break;
            case 8:
            case 9:
                century = 1800;
                break;
            default:
                throw new IllegalArgumentException("Invalid PESEL number");
        }


        return century + year;
    }
}
