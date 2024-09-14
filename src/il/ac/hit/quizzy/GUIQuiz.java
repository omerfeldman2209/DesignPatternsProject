package il.ac.hit.quizzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIQuiz implements IQuiz {

    private String name;
    private List<IQuizQuestion> questions;  // This was null, so let's initialize it.

    private int currentQuestionIndex = 0;
    private int score = 0;

    private JFrame frame;
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JRadioButton[] answerButtons;
    private ButtonGroup answerGroup;
    private JButton nextButton;

    // Constructor to initialize the questions list
    public GUIQuiz() {
        this.questions = new ArrayList<>();  // Initialize the list to avoid NullPointerException.
    }

    @Override
    public void start() {
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Title Label
        titleLabel = new JLabel("", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Question Panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel("");
        questionPanel.add(questionLabel);

        // Answer Buttons
        answerButtons = new JRadioButton[5];
        answerGroup = new ButtonGroup();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new JRadioButton("");
            answerGroup.add(answerButtons[i]);
            questionPanel.add(answerButtons[i]);
        }

        frame.add(questionPanel, BorderLayout.CENTER);

        // Next Button
        nextButton = new JButton("Next");
        frame.add(nextButton, BorderLayout.SOUTH);

        // Next Button Action
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion();
                } else {
                    showFinalScore();
                }
            }
        });

        showQuestion();
        frame.setVisible(true);
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            return;
        }

        QuizQuestion question = (QuizQuestion) questions.get(currentQuestionIndex);
        titleLabel.setText("Question " + (currentQuestionIndex + 1));
        questionLabel.setText(question.getQuestion());

        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answerButtons.length; i++) {
            if (i < answers.size()) {
                answerButtons[i].setText(answers.get(i).getText());
                answerButtons[i].setVisible(true);
            } else {
                answerButtons[i].setVisible(false);
            }
        }

        answerGroup.clearSelection();
    }

    private void checkAnswer() {
        QuizQuestion question = (QuizQuestion) questions.get(currentQuestionIndex);
        List<Answer> answers = question.getAnswers();

        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].isSelected() && answers.get(i).isCorrect()) {
                score++;
                break;
            }
        }
    }

    private void showFinalScore() {
        JOptionPane.showMessageDialog(frame, "Your final score: " + score + "/" + questions.size());
        frame.dispose();
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
        this.questions.add(question);  // Now the questions list is initialized and won't throw NullPointerException.
    }
}
