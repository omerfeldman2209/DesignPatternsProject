package il.ac.hit.quizzy;

public class Program {

    public static void main(String[] args) throws QuizException {
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.TERMINAL);  // Use Terminal (CLI) quiz
        quiz.setName("CLI Quiz Demo");

        // Creating 1st question
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("We Love Canada")
                .setQuestion("Canada starts with...?")
                .addAnswer("Canada starts with the letter 'A'.", false)
                .addAnswer("Canada starts with the letter 'B'.", false)
                .addAnswer("Canada starts with the letter 'C'.", true)
                .addAnswer("Canada starts with the letter 'D'.", false)
                .addAnswer("Canada starts with the letter 'E'.", false);
        IQuizQuestion question1 = builder1.create();

        // Creating 2nd question
        IQuizQuestionBuilder builder2 = new QuizQuestion.Builder();
        builder2.setTitle("We Love Australia")
                .setQuestion("Australia starts with...?")
                .addAnswer("Australia starts with the letter 'A'.", true)
                .addAnswer("Australia starts with the letter 'B'.", false)
                .addAnswer("Australia starts with the letter 'C'.", false)
                .addAnswer("Australia starts with the letter 'D'.", false)
                .addAnswer("Australia starts with the letter 'E'.", false);
        IQuizQuestion question2 = builder2.create();

        // Adding questions to quiz
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        // Start Terminal (CLI) quiz
        quiz.start();
    }
}
