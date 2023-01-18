import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {


    static File file;

    static boolean findBool;

    public static void main(String args[]) throws IOException {
        String target = "а1нийо";
        Path path = Path.of("src/main/java/2022-01-21--russian-words--UTF-8.txt");
        file = new File("src/main/java/f.txt");
        findBool = false;
        Files.lines(path)
                .map(l -> l.split(" "))
                .forEach(line -> {
                    try {
                        findLine( target, line);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        String findBol = String.valueOf(findBool);
        System.out.println(findBol);
    }
    



    private static void findLine(String target, String[] text) throws IOException {
        for (String str: text) {
            if (target.length() == str.length() && find(target,str))
            {
                findBool = true;
                System.out.println(str + " " + str.length() + " " + target.length());
            }
        }

    }

    private static boolean find( String target, String text) {
        Map<Integer,Character> map = new HashMap<>();
        for (int i = 0; i< target.length(); i++) {
            char textChar = text.charAt(i);
            char ch = target.charAt(i);
            int intCh = ch - '0';
            if(Character.isDigit(ch)){
                if (containsCh(target, ch)) {
                    if (map.containsKey(intCh)) {
                        if (!map.get(intCh).equals(textChar))
                            return false;
                    } else {
                        if (map.containsValue(textChar))
                            return false;
                        map.put(intCh, textChar);
                    }
                }
                else
                {
                    return false;
                }
            }
            else {
                if (ch != textChar)
                    return false;
            }
        }
        return true;
    }

    private static boolean containsCh(String target, char ch) {
        return !(target.indexOf(ch) == -1);
    }
}