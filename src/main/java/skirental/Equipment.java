package skirental;

public class Equipment {
    private int sizeOfSkis;
    private int SizeOfBoot;

    public Equipment(int sizeOfSkis, int sizeOfBoot) {
        this.sizeOfSkis = sizeOfSkis;
        SizeOfBoot = sizeOfBoot;
    }

    public int getSizeOfSkis() {
        return sizeOfSkis;
    }

    public int getSizeOfBoot() {
        return SizeOfBoot;
    }
}
