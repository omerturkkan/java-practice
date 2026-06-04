package io.github.omerturkkan.javapractice.basics.operators;

public class QuizScoreEvaluator {
    public static void main(String[] args) {
        String studentName = "John Doe";
        int totalQuestions = 25;
        int correctAnswers = 18;
        int totalTimeSeconds = 745;
        int previousAttempts = 2;
        boolean isFirstTimeUser = false;

        int wrongAnswers = totalQuestions - correctAnswers;
        double scorePercentage = correctAnswers * 100.0 / totalQuestions;
        int totalMinutes = totalTimeSeconds / 60;
        int remainingSeconds = totalTimeSeconds % 60;
        double timePerQuestion = totalTimeSeconds / (double) totalQuestions;

        boolean isPassing = scorePercentage >= 60;

        char letterGrade = scorePercentage >= 90 ? 'A'
                : scorePercentage >= 80 ? 'B'
                : scorePercentage >= 70 ? 'C'
                : scorePercentage >= 60 ? 'D'
                : 'F';

        boolean bonusEligible = isPassing && isFirstTimeUser || previousAttempts == 0;

        int attemptsPost = previousAttempts++;
        int attemptsPre = ++previousAttempts;

        correctAnswers += 2;

        System.out.println("Student Name         : " + studentName);
        System.out.println("Total Questions      : " + totalQuestions);
        System.out.println("Correct Answers      : " + correctAnswers);
        System.out.println("Wrong Answers        : " + wrongAnswers);
        System.out.println("Score Percentage     : " + scorePercentage + "%");
        System.out.println("Time Per Question    : " + timePerQuestion + " seconds");
        System.out.println("Total Time           : " + totalMinutes + " min " + remainingSeconds + " sec");
        System.out.println("Is Passing           : " + isPassing);
        System.out.println("Letter Grade         : " + letterGrade);
        System.out.println("Bonus Eligible       : " + bonusEligible);
        System.out.println("Attempts (post)      : " + attemptsPost);
        System.out.println("Attempts (pre)       : " + attemptsPre);
        System.out.println("Final Attempt Count  : " + previousAttempts);
        System.out.println("Correct After Bonus  : " + correctAnswers);
    }
}