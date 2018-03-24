package group144.kidyankin;

public class StringXorHash implements HashFunction {
    @Override
    public int hash(String element, int mod) {
        int result = 0;
        for (char current: element.toCharArray()) {
            result = result ^ current;
        }
        return result % mod;
    }
}
