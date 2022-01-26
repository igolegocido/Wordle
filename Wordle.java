import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Wordle {
    Scanner sc = new Scanner(System.in);
    private String word;
    private ArrayList<String> wordlist;
    private int gleft;
    private boolean win;
    public Wordle(){
        wordlist = new ArrayList<String>(3300);
        loadWords("words.txt");
        word = wordlist.get((int)(Math.random()*wordlist.size()));
        gleft = 6;
        win = false;
    }
    public void play(){
        instructions();
        while(win == false && gleft>0) {
            System.out.println("You have " +gleft+ " guesses left. Make a guess: \n");
            String in = sc.nextLine();
            in = in.toUpperCase();
            if(in.equals(word)){
                win = true;
            }else if(!checkValid(in)){
                System.out.println("Input invalid");
            }else{
                guess(in);
            }
        }
        if(win){
            System.out.println("Correct! The word was: "+word+". Good game!");
        }else{
            System.out.println("You lost! The word was: "+word+". Better luck next time!");
        }
    }
    public void guess(String str) {
        for(int i =0;i<str.length();i++){
            System.out.print(str.substring(i,i+1));
            if(str.substring(i,i+1).equals(word.substring(i,i+1))){
                System.out.print("! ");
            }else if(word.contains(str.substring(i,i+1))){
                System.out.print("? ");
            }else{
                System.out.print(" ");
            }
        }
        gleft--;
        System.out.println();
    }
    public boolean checkValid(String str){
        if(str.length()!=5||!wordlist.contains(str)){
            return false;
        }else{
            return true;
        }
    }
    public void instructions(){
        System.out.println("Guess the WORDLE in 6 tries.\n" +
                "Each guess must be a valid 5 letter word. Hit the enter button to submit.\n" +
                "After each guess, the letters in the word will change to show how close your guess was to the word.\n");
        System.out.println("Examples\n" +
                "The letter W is in the word and in the correct spot.\n" +
                "W! E A R Y\n"+
                "The letter I is in the word but in the wrong spot.\n" +
                "P I? L L S\n"+
                "Letters with no marking are not in any spot");
    }
    public void loadWords(String filename){
        File wordfile = new File(filename);
        try{
            Scanner fileScanner = new Scanner(wordfile);
            while(fileScanner.hasNext()){
                String w = fileScanner.nextLine();
                if(w.length()==5){
                    wordlist.add(w.toUpperCase());
                }
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}