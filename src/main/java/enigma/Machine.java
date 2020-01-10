package enigma;

public class Machine {

    private Rotor leftRotor;
    private Rotor middleRotor;
    private Rotor rightRotor;
    private Reflector reflector;

    /*
        This method will init the rotors of the machine. This will affect to
        the current Enigma machine its rotors and reflector. It takes in parameters:
            - the reflector of the machine ;
            - the three rotors of the machine.
        The three rotors will be assign to their position in the machine.
    */
    public void initRotors(Reflector reflector, Rotor left, Rotor middle, Rotor right) {
        this.reflector = reflector;
        leftRotor = left;
        middleRotor = middle;
        rightRotor = right;
    }

    /*
        This function will set the position of the rotors and reflector. It inits
        the Enigma machine in its default settings to code the first message.
    */
    public void setPositions(String setting) {
        char[] charSettings = setting.toCharArray();
        reflector.setPosition(Rotor.toIndex(charSettings[0]));
        leftRotor.setPosition(Rotor.toIndex(charSettings[1]));
        middleRotor.setPosition(Rotor.toIndex(charSettings[2]));
        rightRotor.setPosition(Rotor.toIndex(charSettings[3]));
    }

    /*
        Configure the Enigma machine by calling the two previous functions.
    */
    public void configure(Reflector reflector, Rotor left, Rotor middle, Rotor right, String setting) {
        this.initRotors(reflector, left, middle, right);
        this.setPositions(setting);
    }

    /*
        This method converts a letter into a coded one and return the coded string
        by adding all the coded letters to a resultant coded message (l. 53).
    */
    public String convert(String msg) {
        msg = msg.toUpperCase();
        char[] msgChars = msg.toCharArray();
        String result = "";
        for (char c : msgChars) {
            result += convertChar(c);
        }
        return result;
    }

    char convertChar(char c) {
        advanceRotors();
        int charIndex = Rotor.toIndex(c);
        int output;
        output = rightRotor.convertForward(charIndex);
        output = middleRotor.convertForward(output);
        output = leftRotor.convertForward(output);
        output = reflector.convertForward(output);
        output = leftRotor.convertBackward(output);
        output = middleRotor.convertBackward(output);
        output = rightRotor.convertBackward(output);
        return Rotor.toLetter(output);
    }

    void advanceRotors() {
        boolean advanceLeft = false;
        boolean advanceMiddle = false;
        boolean advanceRight = true;
        if (leftRotor.atNotch()) {
        }
        if (middleRotor.atNotch()) {
            advanceMiddle = true;
            advanceLeft = true;
        }
        if (rightRotor.atNotch()) {
            advanceMiddle = true;
            advanceRight = true;
        }
        if (advanceLeft) {
            leftRotor.advance();
        }
        if (advanceRight) {
            rightRotor.advance();
        }
        if (advanceMiddle) {
            middleRotor.advance();
        }
    }
}
