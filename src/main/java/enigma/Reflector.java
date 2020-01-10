package enigma;

/** Class that represents a reflector in the enigma.
 *  @author
 */
public class Reflector extends Rotor {
	
    int[] reflection;

    /*
        This method removes spaces in the string to code it: this character chain
        will pass over the reflector and back to the three rotors. The new reflector
        configuration is return by the method.
    */
    public static Reflector reflectorFactory(String str){
        char[] s = str.trim().replace(" ", "").toCharArray();
        int[] cipher = new int[26];
        for (int i = 0; i< 26; i++){
            cipher[i] = toIndex(s[i]);
        }
        return new Reflector(cipher);
    }

    private Reflector(int[] r){
        reflection = r;
    }
    
    /*
        These two functions have the same role than the two present in the Rotor
        class, but they are applied to the current reflector.
    */
    public int convertForward(int p) {
        return ((reflection[((p)%26+26)%26])%26+26)%26;
    }

    @Override
    public int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void advance() {
        
    }

}
