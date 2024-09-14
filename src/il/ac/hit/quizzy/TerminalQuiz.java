package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz {

    private String name;
    private List<IQuizQuestion> questions;
    private int score = 0;

    // Constructor to initialize the questions list
    public TerminalQuiz() {
        this.questions = new ArrayList<>();
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);  // Scanner for reading user input
        score = 0;  // Reset score for the quiz

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = (QuizQuestion) questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getTitle());
            System.out.println(question.getQuestion());

            // Display possible answers
            List<Answer> answers = question.getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                System.out.println((j + 1) + ": " + answers.get(j).getText());
            }

            // Get user answer with input validation
            int userAnswer = getUserAnswer(scanner, answers.size());

            // Check if the selected answer is correct
            if (answers.get(userAnswer - 1).isCorrect()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong!\n");
            }
        }

        // Quiz finished, print final score
        System.out.println("Quiz finished! Your score: " + score + "/" + questions.size());
    }

    // Method to handle input validation for answer selection
    private int getUserAnswer(Scanner scanner, int maxOption) {
        int userAnswer = -1;

        // Loop until valid input is entered
        while (true) {
            System.out.print("Your answer (enter the number): ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= maxOption) {
                    break;  // Valid input, exit the loop
                } else {
                    System.out.println("Invalid input, please enter a number between 1 and " + maxOption + ".");
                }
            } else {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();  // Clear the invalid input
            }
        }

        return userAnswer;
    }

    @Override
    public void setName(String text) {
        this.name = text;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        this.questions.add(question);
    }
}
