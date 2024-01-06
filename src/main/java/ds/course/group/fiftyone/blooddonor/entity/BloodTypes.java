package ds.course.group.fiftyone.blooddonor.entity;

public enum BloodTypes {

    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String bloodTypes;

    BloodTypes(String bloodTypes) {
        this.bloodTypes = bloodTypes;
    }

    public String getBloodType() {
        return bloodTypes;
    }
}
