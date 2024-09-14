package il.ac.hit.quizzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCSVQuizFilesDAOTest {

    private SimpleCSVQuizFilesDAO dao;
    private IQuiz quiz;

    @BeforeEach
    public void setUp() {
        dao = SimpleCSVQuizFilesDAO.getInstance();
        QuizFactory factory = new QuizFactory();
        quiz = factory.createQuiz(QuizType.TERMINAL);
        quiz.setName("Sample Quiz");

        // Creating a question to add to the quiz
        IQuizQuestionBuilder builder = new QuizQuestion.Builder();
        builder.setTitle("Math Test")
               .setQuestion("What is 2 + 2?")
               .addAnswer("3", false)
               .addAnswer("4", true);

        IQuizQuestion question = builder.create();
        quiz.addQuestion(question);
    }

    @Test
    public void testSaveAndLoadQuiz() throws QuizException {
        String fileName = "test_quiz.data";

        // Save the quiz to file
        dao.saveQuizToFile(quiz, fileName);

        // Load the quiz back from file
        IQuiz loadedQuiz = dao.loadQuizFromFile(fileName);

        assertNotNull(loadedQuiz);
        assertEquals("Sample Quiz", loadedQuiz.getName());

        // Clean up the file after test
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSingletonInstance() {
        IQuizFilesDAO dao1 = SimpleCSVQuizFilesDAO.getInstance();
        IQuizFilesDAO dao2 = SimpleCSVQuizFilesDAO.getInstance();

        assertSame(dao1, dao2);  // Both should refer to the same instance (Singleton pattern)
    }
}
