// You should **not** update any call signatures in this file
// only modify the body of each function
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
class Conversation implements ConversationRequirements {

  // Attributes 
  private int round;
  private ArrayList<String> transcript;
  private String[] response;
  private Scanner inputReader;
  private Random random;
  /**
   * Constructor 
   */
  public Conversation(int r) {
    this.round = r;
    this.transcript = new ArrayList<String>();
    this.inputReader = new Scanner(System.in);
    this.random = new Random();
    this.response = new String[]{
      "Mmm-hm...", "That is interesting, tell me more." , "Interesting!", "Sounds great!"};
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    String greeting = "Hi there! What's in your mind?";
    System.out.println(greeting);
    transcript.add(greeting);
    for (int i = 0; i < round; i++){
      String userInput = inputReader.nextLine();
      transcript.add(userInput);
      String answer = respond(userInput);
      System.out.println(answer);
      transcript.add(answer);
    }
    String goodbye = "See ya!";
    System.out.println(goodbye);
    transcript.add(goodbye);
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT:");
    for (String line:transcript){
      System.out.println(line);
    }

  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String returnString = "";
    String[] words = inputString.split(" ");
    boolean change = false;
    for (int i = 0; i < words.length; i++){
      if (words[i].equals("I")){
        words[i] = "you";
        change = true;
      }
      else if (words[i].equals("me")){
        words[i] = "you";
        change = true;
      }
      else if (words[i].equals("am")){
        words[i] = "are";
        change = true;
      }
      else if (words[i].equals("you")){
        words[i] = "I";
        change = true;
      }
      else if (words[i].equals("my")){
        words[i] = "your";
        change = true;
      }
      else if (words[i].equals("your")){
        words[i] = "my";
        change = true;
      }
    }
    if (change){
      returnString = String.join(" ", words)+"?";
      return returnString; 
    }
    else{
      int randomIndex = random.nextInt(response.length);
      returnString = response[randomIndex];
      return returnString;
    }
  }

  public static void main(String[] arguments) {
    Scanner input = new Scanner(System.in);
    System.out.println("How many rounds?");
    int rounds = input.nextInt();
    Conversation myConversation = new Conversation(rounds);
    myConversation.chat();
    myConversation.printTranscript();

  }
}
