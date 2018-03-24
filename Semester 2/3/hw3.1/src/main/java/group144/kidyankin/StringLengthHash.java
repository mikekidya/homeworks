package group144.kidyankin;

public class StringLengthHash implements HashFunction {
    @Override
    public int hash(String element, int mod) {
        return element.length() % mod;
    }
}
