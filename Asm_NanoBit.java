import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * OFFICIAL NANOBIT ARCHITECTURE ASSEMBLER
 * FREE TO USE, MODIFY AND REDISTRIBUITE
 * LITHAX SOFTWARE FOUNDATION
 * @see <a href="https://www.github.com/Lithax/NanoBit">NanoBit</a>
 * @version 26-11-24
 * @author Lithax
 */
public class Asm_NanoBit {
    public static final HashMap<String, String> instructions = new HashMap<String, String>();
    public static final HashMap<String, String> registers = new HashMap<String, String>();
    static {
        instructions.put("sum", "000");
        instructions.put("sub", "001");
        instructions.put("str", "010");
        instructions.put("out", "011");
        registers.put("eax", "00");
        registers.put("edx", "01");
    }

    public static String assemble(String src) throws Exception {
        String bin = "";
        String[] splitted = src.split("\n");
        for (String line : splitted) {
            line = line.trim(); // Remove any leading or trailing whitespace
            if (line.isEmpty()) {
                continue; // Skip empty lines
            }
            String[] split = line.split(" ");
            for (String keyword : split) {
                if (keyword.isEmpty()) {
                    continue;
                }
                String assembled = instructions.get(keyword) == null ? registers.get(keyword) : instructions.get(keyword);
                if (assembled == null) {
                    throw new Exception("Illegal Instruction or Register: " + keyword);
                } else {
                    bin += assembled + " ";
                }
            }
            bin = bin.trim() + "\n";
        }
        bin = bin.trim();
        return bin;
    }
    

    public static String read(String name) {
        try {
            File e = new File(name);
            FileInputStream stream = new FileInputStream(e);
            byte[] buffer = new byte[(int)e.length()];
            stream.read(buffer);
            stream.close();
            return new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void write(String name, String content) {
        try {
            File e = new File(name);
            FileOutputStream stream = new FileOutputStream(e);
            stream.write(content.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printHelp() {
        String reset = "\u001B[0m"; // Reset color
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String cyan = "\u001B[36m";
        String bold = "\u001B[1m";
    
        System.out.println(bold + cyan + "NanoBit Assembler Help" + reset);
        System.out.println(green + "Assembler Overview:" + reset);
        System.out.println("NanoBit is a lightweight assembly language for the NanoBit CPU architecture.");
        System.out.println("You can use this assembler to convert assembly code into binary code.");
        System.out.println("It supports basic operations like addition, subtraction, storing values, and outputting register values.");
        
        System.out.println("\n" + yellow + "Instructions:" + reset);
        System.out.println(green + "sum" + reset + " : " + blue + "000" + reset + " - Adds two values");
        System.out.println(green + "sub" + reset + " : " + blue + "001" + reset + " - Subtracts one value from another");
        System.out.println(green + "str" + reset + " : " + blue + "010" + reset + " - Stores a value to a register");
        System.out.println(green + "out" + reset + " : " + blue + "011" + reset + " - Stores the 4-bit input in the specified register");
    
        System.out.println("\n" + yellow + "Registers:" + reset);
        System.out.println(green + "eax" + reset + " : " + blue + "00" + reset + " - General-purpose register");
        System.out.println(green + "edx" + reset + " : " + blue + "01" + reset + " - Another general-purpose register");
    
        System.out.println("\n" + cyan + "CPU Information:" + reset);
        System.out.println("The NanoBit CPU architecture supports a limited set of instructions and registers.");
        System.out.println("It can execute basic arithmetic, memory operations, and output register values.");
        System.out.println("Instructions are encoded as 3-bit binary values, and registers are represented by 2-bit binary values.");
        
        System.out.println("\n" + green + "Usage:" + reset);
        System.out.println("1. To assemble code: `Asm_NanoBit -o input.asm output.bin`");
        System.out.println("2. To print binary output to console: `Asm_NanoBit -o input.asm -p`");
        System.out.println("3. For help: `Asm_NanoBit help`");
    }
    

    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "help":
                    printHelp();
                    break;
                case "-o":
                    try {
                        String bin = assemble(args.length == 3 ? read(args[1]) : "");
                        if(args[2].equals("-p")) {
                            System.out.println(bin);
                        } else {
                            write(args[2], bin);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Unknown Argument: "+args[0]);
                    break;
            }
        }
    }
}