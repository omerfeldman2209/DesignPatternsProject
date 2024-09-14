package il.ac.hit.quizzy;

import java.io.*;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {

    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO() {
        // Private constructor to prevent instantiation
    }

    public static SimpleCSVQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Quiz Name: " + quiz.getName());
            // Logic to save the quiz content
        } catch (IOException e) {
            throw new QuizException("Error saving quiz to file: " + e.getMessage());
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String quizName = reader.readLine().split(":")[1].trim();
            // Logic to load the quiz content
            IQuiz quiz = new TerminalQuiz();  // or GUIQuiz depending on your design
            quiz.setName(quizName);
            return quiz;
        } catch (IOException e) {
            throw new QuizException("Error loading quiz from file: " + e.getMessage());
        }
    }
}
