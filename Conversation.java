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
    /** The default start of conversasion. */
    String greeting = "Hi there! What's in your mind?";
    System.out.println(greeting);
    /** record the start sentence in the transcript. */
    transcript.add(greeting);
    /** loop until reaching the round number.
     */
    for (int i = 0; i < round; i++){
      /* get users' words */
      String userInput = inputReader.nextLine();
      /* record users' words */
      transcript.add(userInput);
      /*in each round, use respond function that I wrote below to either getting a mirror response or getting a random response.  */
      String answer = respond(userInput);
      System.out.println(answer);
      /* record */
      transcript.add(answer);
    }
    /* after reaching the round number, print goodbye message. */
    String goodbye = "See ya!";
    System.out.println(goodbye);
    /*record goodbye message. */
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
    /*create a string variable to catch the return massage. */
    String returnString = "";
    /* divide users' sentence by space, so each element of String[] is a word. */
    String[] words = inputString.split(" ");
    /* creat a boolean variable to help decide what kind of message should we return(mirror/random) */
    boolean change = false;
    /*go through each word of users' sentence, and detect the words that need to be changed, and then change them.
    If the words are changeed, change the boolean value. */
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
    /*if the words in user's sentence are changed, join the spilt words by space and return the mirror message. */
    if (change){
      returnString = String.join(" ", words)+"?";
      return returnString; 
    }
    /*if the words are not changed, return a random message. */
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
