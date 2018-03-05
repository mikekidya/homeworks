package group144.kidyankin;

public class StringPolynomialHash implements HashFunction {
    @Override
    public int hash(String element, int mod) {
        int multiplier = 101 % mod;
        int result = 0;
        for (char current: element.toCharArray()) {
            result = (result + multiplier * current) % mod;
        }
        return result;
    }
}
