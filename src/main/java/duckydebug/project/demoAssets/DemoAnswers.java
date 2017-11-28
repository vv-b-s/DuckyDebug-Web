package duckydebug.project.demoAssets;

import duckydebug.project.entity.Answer;
import duckydebug.project.entity.Log;
import duckydebug.project.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class DemoAnswers {
    private static String[][] demoAnswerStrings = new String[][]{
        new String[]{
                "In my personal CSS file at personalStylings>toolbar.css",
                "Not sure but I think it is related with the way I refer to the CSS in the HTML file",
                "I'm searching on the web for solution of this issue and tried some solutions for with similar issues on StackOverflow",
                "There are some more solutions I still have not tried. Maybe I should check them out",
                "- StackOverflow\n- W3Schools",
                "-Can't override CSS file\nHow to override CSS with CSS\n- mdc-toolbar bot getting overridden",
                "Maybe I should see an Reddit"

        },
        new String[]{
                "In my ActivityController class and all references to it",
                "AS lint analysis say that using static references to activities causes memory leakages",
                "I'm investigating the issue and learning more about how to access and modify data on stacked activities",
                "I'm looking at topics over StackOverflow which seem promising",
                "StackOverflow",
                "- Android Studio lint saying static activities may cause leakage\nHow to refer to parent activity in Android",
                "I should just keep looking for solutions but I have all the needed information"
        },

        new String[]{
            "In a ViewLogActivity",
            "The name of the column is mistaken but I don't know exactly why",
            "Searching over the web it looks like SQLite column naming conventions are a lot more different than what I named my" +
                    " properties and I need to give a different name of column in the query",
            "I need to replace the query column names with the correct ones",
            "-StackOverflow\n-The ORM Sugar documentation",
            "-SQLite no column exception\n-ORM Sugar can't send query an getting column exception",
            "Not for now but if I have any other ideas I will update this section"
        }
    };

    /**
     * Links answers with question ids.
     * @param questions
     * @return
     */
    public static ArrayList<ArrayList<Answer>> getAnswersWithQuestions(List<Question> questions){
        ArrayList<ArrayList<Answer>> answers = new ArrayList<>();

        //Make as many answers as answers sets per log exist
        for(String[] demoAnswers:demoAnswerStrings){

            //Each set has its own answers
            ArrayList<Answer> currentAnswers = new ArrayList<>();

            //Set index to keep track of questions to relate each answer with a question
            int questionIndex = 0;

            //Each answer has its own string
            for(String demoString:demoAnswers){
                // Log will be edited from other class
                currentAnswers.add(new Answer(demoString,questions.get(questionIndex), new Log()));
                questionIndex++;
            }
            answers.add(currentAnswers);
        }

        return answers;
    }
}
