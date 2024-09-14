package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {

    private String title;
    private String question;
    private List<Answer> answers;

    protected QuizQuestion(String title, String question, List<Answer> answers) {
        this.title = title;
        this.question = question;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public static class Builder implements IQuizQuestionBuilder {

        private String title;
        private String question;
        private List<Answer> answers = new ArrayList<>();

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            this.title = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            this.question = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            this.answers.add(new Answer(text, correct));
            return this;
        }

        @Override
        public IQuizQuestion create() {
            return new QuizQuestion(title, question, answers);
        }
    }
}
