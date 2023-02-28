
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Quiz_Project_on_Java {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner option = new Scanner(System.in);
        //--------Will show program output-----------
        System.out.println("Please Choose an Option");
        System.out.println("\nOption 1: Add Quiz\n");
        System.out.println("Option 2: Start Quiz");

        int choice = option.nextInt();

        switch (choice){
            case 1: addQuiz();
                break;
            case 2: startQuiz();
                break;

        }
    }
    public static void addQuiz() throws IOException, ParseException {

        char ch = 'y';

        String fileName = "./src/main/resources/QuestionFile.json";

        Scanner input = new Scanner(System.in);

        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));

            JSONObject addQuiz = new JSONObject();
            //If user select option 1, then system will tell user to input a question, 4 options and correct answer to save data in a quiz bank.
            System.out.println("\nPlease Add a Question Here:");
            addQuiz.put("Question: ", input.nextLine());
            System.out.println("\nPlease Enter The options:");
            System.out.println("Option a: ");
            addQuiz.put("Option a", input.nextLine());
            System.out.println("Option b: ");
            addQuiz.put("Option b", input.nextLine());
            System.out.println("Option c: ");
            addQuiz.put("Option c", input.nextLine());
            System.out.println("Option d: ");
            addQuiz.put("Option d", input.nextLine());


            System.out.println("Please Give The Correct Answer: ");
            addQuiz.put("Correct Ans ", input.nextLine());


            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(addQuiz);

            FileWriter file = new FileWriter(fileName);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

            System.out.println("\nYour Quiz Qustion is saved. Do you want to add more Questions? (y/n)");
            ch = input.nextLine().charAt(0);
        }

        while (ch != 'n');
        System.out.println("\nThanks For Adding The Questions");

    }

    //-------When user wants to select option 2 to start the quiz---------
    public static void startQuiz() throws IOException, ParseException {

        System.out.println("\nYou will be asked 5 Questions. Are You Ready? (y/n)");
        int count = 0;
        Scanner input = new Scanner(System.in);
        char ready = input.nextLine().charAt(0);
        Random ran = new Random();

        if(ready == 'y') {

            for (int i = 1; i <= 5; i++) {

                int queNo = ran.nextInt(25);


                String fileName = "./src/main/resources/QuestionFile.json";
                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(new FileReader(fileName));

                JSONArray jsonArray = (JSONArray) obj;

                JSONObject jsonObject = (JSONObject) jsonArray.get(queNo);
                //---------Question will be given------------
                System.out.println("\nQuestion - " + i + ": " + (String) jsonObject.get("Question: "));
                //---------Option will be given--------------
                String option1 = (String) jsonObject.get("Option a");
                System.out.println("Option a: " + option1);
                String option2 = (String) jsonObject.get("Option b");
                System.out.println("Option b: " + option2);
                String option3 = (String) jsonObject.get("Option c");
                System.out.println("Option c: " + option3);
                String option4 = (String) jsonObject.get("Option d");
                System.out.println("Option d: " + option4);


                System.out.println("\nPlease Give Your Answer");

                String userAns = input.nextLine();
                String realAns = (String) jsonObject.get("Correct Ans ");

                if (userAns.equals(realAns)) {
                    System.out.println("\nYour Answer is CORRECT");
                    count++;
                } else {
                    System.out.println("\nYour Answer is INCORRECT");
                }

            }
            System.out.println("\nYou Got " + count + " marks out of 5.");
            System.out.println("\nThanks for your Participation.");
        }

        else
        {
            System.out.println("\nYou didn't Participate in the Quiz.");
        }

    }

}
