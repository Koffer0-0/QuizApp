package com.temirlan.quizapp

object Constants {

    const val USER_NAME: String = "User name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestion(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val question = "Name of this person is..."
        val q1 = Question (
            1,
            question,
            R.drawable.ig_q1,
            "Batman",
            "Spider-man",
            "Iron man",
            "Naruto",
            3

        )
        val q2 = Question (
            2,
            question,
            R.drawable.ig_q2,
            "Hulk",
            "Thor",
            "Venom",
            "Doctor Strange",
            4

        )
        val q3 = Question (
            3,
            "They are...",
            R.drawable.ig_q3,
            "Titans",
            "League of Justice",
            "Avengers",
            "X-men",
            4
        )
        val q4 = Question (
            4,
            "Main antagonist of this film",
            R.drawable.ig_q4,
            "Hulk",
            "Loki",
            "Thanos",
            "Ultron",
            3
        )
        val q5 = Question (
            5,
            "Material of Cap's shield",
            R.drawable.ig_q5,
            "Iron",
            "Plastic",
            "Vibranium",
            "Adamantium",
            3
        )
        val q6 = Question (
            6,
            "Also who has device from Vibranium?",
            R.drawable.ig_q6,
            "Thor",
            "Spider-man",
            "Black Panther",
            "Superman",
            3
        )
        val q7 = Question (
            7,
            "First film of Spider-man was released in",
            R.drawable.ig_q7,
            "2012",
            "2002",
            "2003",
            "2009",
            2
        )

        questionList.add(q1)
        questionList.add(q2)
        questionList.add(q3)
        questionList.add(q4)
        questionList.add(q5)
        questionList.add(q6)
        questionList.add(q7)
        return questionList
    }
}