package il.ac.hit.quizzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizQuestionTest {

    private IQuizQuestionBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new QuizQuestion.Builder();
    }

    @Test
    public void testCreateQuizQuestion() {
        builder.setTitle("Test Question")
                .setQuestion("What is 2 + 2?")
                .addAnswer("3", false)
                .addAnswer("4", true)
                .addAnswer("5", false);

        IQuizQuestion question = builder.create();

        assertNotNull(question);
        assertTrue(question instanceof QuizQuestion);
        QuizQuestion quizQuestion = (QuizQuestion) question;

        assertEquals("Test Question", quizQuestion.getTitle());
        assertEquals("What is 2 + 2?", quizQuestion.getQuestion());
        assertEquals(3, quizQuestion.getAnswers().size());
    }

    @Test
    public void testCorrectAnswer() {
        builder.setTitle("Math Test")
                .setQuestion("What is 2 + 2?")
                .addAnswer("3", false)
                .addAnswer("4", true);

        QuizQuestion question = (QuizQuestion) builder.create();

        // Test if the correct answer is marked as true
        assertTrue(question.getAnswers().get(1).isCorrect());
        assertFalse(question.getAnswers().get(0).isCorrect());
    }
}
