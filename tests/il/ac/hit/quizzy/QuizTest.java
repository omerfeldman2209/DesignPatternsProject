package il.ac.hit.quizzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    private IQuiz quiz;

    @BeforeEach
    public void setUp() {
        QuizFactory factory = new QuizFactory();
        quiz = factory.createQuiz(QuizType.TERMINAL);
        quiz.setName("Sample Quiz");

        // Create and add questions to quiz
        IQuizQuestionBuilder builder = new QuizQuestion.Builder();
        builder.setTitle("Math Test")
                .setQuestion("What is 2 + 2?")
                .addAnswer("3", false)
                .addAnswer("4", true);

        IQuizQuestion question = builder.create();
        quiz.addQuestion(question);
    }

    @Test
    public void testQuizName() {
        assertEquals("Sample Quiz", quiz.getName());
    }

    @Test
    public void testAddQuestion() {
        assertNotNull(quiz);
        assertEquals(1, ((TerminalQuiz) quiz).questions.size());
    }
}
