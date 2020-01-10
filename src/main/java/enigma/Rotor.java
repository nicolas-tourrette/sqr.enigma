package enigma;

public class Rotor {

    private int position;
    private int[] cipher = new int[26];
    private int[] bcipher = new int[26];
    private int notch1 = -1;
    private int notch2 = -1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int posn) {
        position = posn;
    }
    
    /*
        This function will remove spaces in the string to code, then code the message
        by virtually rotating the rotor and return the new configuration of the rotor.
        To return the new rotor configuration, this method will create another rotor
        with the rigth configuration by calling its constructor.
    */
    public static Rotor rotorFactory(String str, String notches){
        char[] s = str.trim().replace(" ", "").toCharArray();
        int[] cipher = new int[26];
        for (int i = 0; i< 26; i++){
                cipher[i] = toIndex(s[i]);
        }
        s = notches.trim().replace(" and ", "").toCharArray();
        if (s.length == 2){
            return new Rotor(cipher, toIndex(s[0]), toIndex(s[1]));
        } else {
            return new Rotor(cipher, toIndex(s[0]));
        }
    }
	
    private Rotor(int[] c, int notch1, int notch2) {
        this.notch1 = notch1;
        this.notch2 = notch2;
        cipher = c;
        createBCipher();
    }

    private Rotor(int[] c, int notch1) {
        this.notch1 = notch1;
        cipher = c;
        createBCipher();
    }

    protected Rotor() {

    }

    /*
        These two functions convert a position to code or decode a character.
    */
    public int convertForward(int p) {
        return ((cipher[((p+position)%26+26)%26]-position)%26+26)%26;
    }

    public int convertBackward(int e) {
        return ((bcipher[((e+position)%26+26)%26]-position)%26+26)%26;
    }
    
    /*
        Give the new position of the rotor: at each letter, the position of the
        rotor is increased of one step.
    */
    public void advance() {
        position = (position+1) % 26;
    }
    
    protected boolean atNotch() {
        return (position == notch1 || position == notch2);
    }

    protected static char toLetter(int p) {
        return (char)(p + 'A');
    }

    protected static int toIndex(char c) {
        return c - 'A';
    }
    
    private void createBCipher() {
        for(int i =0; i<26; i++)
            bcipher[cipher[i]] = i;
    }

}
