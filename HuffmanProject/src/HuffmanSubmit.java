import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class UR_Node implements Comparable<UR_Node> {
    Character key;
    Integer freq;
    UR_Node left;
    UR_Node right;

    UR_Node(Character key, Integer freq, UR_Node left, UR_Node right){
        this.key = key;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(UR_Node o) {
        int freqComparison = Integer.compare(this.freq, o.freq);
        if (freqComparison != 0) {
            return freqComparison;
        } else {
            return this.key.compareTo(o.key);
        }
    }
}

public class HuffmanSubmit implements Huffman {
    UR_Node root;
    HashMap<Character, Integer> initialMap = new HashMap<>();
    PriorityQueue<UR_Node> pq;
    HashMap<Character, String> freqMap;
    private String encodedMessage = "";
    private String decodedMessage = "";

    public void buildFreqMap(String word){
        for(int i = 0; i < word.length(); i++) {
            char currLetter = word.charAt(i);
            if(!initialMap.containsKey(currLetter)){
                initialMap.put(currLetter, 1);
            } else {
                Integer currVal = initialMap.get(currLetter);
                initialMap.put(currLetter, currVal + 1);
            }
        }
        pq = sortMap(initialMap);
        root = makeTree(pq);
        freqMap = mapFreqOfLetters(root);
    }

    public PriorityQueue<UR_Node> sortMap(HashMap<Character, Integer> map){
        PriorityQueue<UR_Node> thisPQ = new PriorityQueue<>();

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            thisPQ.add(new UR_Node(entry.getKey(), entry.getValue(), null, null));
        }
        return thisPQ;
    }

    public UR_Node makeTree(PriorityQueue<UR_Node> pq) {
        while(pq.size() > 1) {
            UR_Node newNode1 = pq.remove();
            UR_Node newNode2 = pq.remove();
            int nodeFreq = newNode1.freq + newNode2.freq;
            UR_Node node = new UR_Node(' ', nodeFreq, newNode1, newNode2);
            pq.add(node);
        }
        return pq.remove();
    }

    public HashMap<Character, String> mapFreqOfLetters(UR_Node node){
        HashMap<Character, String> map = new HashMap<>();
        createFreqCode(node, map, "");
        return map;
    }

    public void createFreqCode(UR_Node node, HashMap<Character, String> map, String s){
        if(node.left == null && node.right == null){
            map.put(node.key,s);
            return;
        }
        createFreqCode(node.left, map, s + '0');
        createFreqCode(node.right, map, s + '1');
    }

    public void createMessage(String word){
        for(int i = 0; i < word.length(); i++){
            char currChar = word.charAt(i);
            encodedMessage = encodedMessage + freqMap.get(currChar);
        }
        encodedMessage = encodedMessage + " ";
    }

    public void d (String coded) {
        UR_Node curr = root;
        for (int i = 0; i < coded.length(); i++) {
            curr = coded.charAt(i) == '1' ? curr.right : curr.left;
            if (curr.left == null && curr.right == null) {
                decodedMessage += curr.key;
                curr = root;
            }
        }
        decodedMessage += " ";
    }

	public void encode(String inputFile, String outputFile, String freqFile) throws IOException {
		// TODO: Your code here
        File input = new File(inputFile);
        File output = new File(outputFile);
        File freq = new File(freqFile);
        FileWriter fileWriter = new FileWriter(freq);
        FileWriter fileWriter1 = new FileWriter(output);

        Scanner scnr = new Scanner(input);
        while(scnr.hasNext()){
            String currWrd = scnr.next();
            buildFreqMap(currWrd);
        }

        Scanner scn = new Scanner(input);
        while(scn.hasNext()){
            String currWord = scn.next();
            createMessage(currWord);
        }

        System.out.println(initialMap);
        System.out.println(freqMap);
        System.out.println(encodedMessage);

        StringBuilder formattedMap = new StringBuilder();
        for (Character key : initialMap.keySet()) {
            formattedMap.append(Integer.toBinaryString((int) key)).append(':').append(initialMap.get(key)).append("\n");
        }

        fileWriter.write(formattedMap.toString());
        fileWriter.close();
        fileWriter1.write(encodedMessage);
        fileWriter1.close();
   }

   public void decode(String inputFile, String outputFile, String freqFile) throws IOException {
		// TODO: Your code here
       File input = new File(inputFile);
       Scanner s = new Scanner(input);
       File output = new File(outputFile);
       FileWriter fileWriter = new FileWriter(output);

       while(s.hasNext()){
           String currWrd = s.next();
           d(currWrd);
       }
       fileWriter.write(decodedMessage);
       fileWriter.close();
       System.out.println(decodedMessage);
   }

   public static void main(String[] args) throws IOException {
      Huffman  huffman = new HuffmanSubmit();
       huffman.encode("src/Test", "enc.txt", "freq.txt");
      huffman.decode("enc.txt", "dec.txt", "freq.txt");

   }
}
