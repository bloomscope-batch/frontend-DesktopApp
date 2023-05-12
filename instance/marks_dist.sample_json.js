var sample_data = {

    // none means the marking distribution is not available
    //take it as 0, hence the student_score = top_score = avg_score = 0

    // the maximum marks that can be obtained
    // to be represented as table not as a graph

    "test_distribution" : {

        "CQR" : {
            "maximum_marks" : 6,
            "parametric_distribution" : {
                "recall_value" : 1,
                "recognizing_factor" : 2,
                "practice_level" : 2,
                "scrutinizing_power" : none,
                "judgemental_value" : 1,
                "origination_power" : none
            }
        },

        "AQR" : {
            "maximum_marks" : 6,
            "parametric_distribution" : {
                "utterance_level" : 1,
                "decision_making_capability" : 3,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 2
            }
        },

        "ESQR" : {
            "maximum_marks" : 6,
            "parametric_distribution" : {
                "utterance_level" : none,
                "decision_making_capability" : 5,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 1
            }
        }
    },

    // marks secured by a student

    "my_score" : {

        "total_marks_secured" : 11,

        "CQR" : {
            "marks_secured" : 4,
            "parametric_distribution" : {
                "recall_value" : 1,
                "recognizing_factor" : 2,
                "practice_level" : 1,
                "scrutinizing_power" : none,
                "judgemental_value" : 0,
                "origination_power" : none
            }
        },

        "AQR" : {
            "marks_secured" : 3,
            "parametric_distribution" : {
                "utterance_level" : 0,
                "decision_making_capability" : 3,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 0
            }
        },

        "ESQR" : {
            "marks_secured" : 4,
            "parametric_distribution" : {
                "utterance_level" : none,
                "decision_making_capability" : 4,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 0
            }
        }
    },

    // marks secured by topper

    "top_score" : {

        "CQR" : {
            "marks_secured" : 6,
            "parametric_distribution" : {
                "recall_value" : 1,
                "recognizing_factor" : 2,
                "practice_level" : 2,
                "scrutinizing_power" : none,
                "judgemental_value" : 1,
                "origination_power" : none
            }
        },

        "AQR" : {
            "marks_secured" : 6,
            "parametric_distribution" : {
                "utterance_level" : 1,
                "decision_making_capability" : 3,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 2
            }
        },

        "ESQR" : {
            "marks_secured" : 6,
            "parametric_distribution" : {
                "utterance_level" : none,
                "decision_making_capability" : 5,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 1
            }
        }
    },

    // average marks secured by all students

    "avg_score" : {

        "CQR" : {
            "avg_score" : 4,
            "parametric_distribution" : {
                "recall_value" : 1,
                "recognizing_factor" : 2,
                "practice_level" : 1,
                "scrutinizing_power" : none,
                "judgemental_value" : 0,
                "origination_power" : none
            }
        },

        "AQR" : {
            "avg_score" : 3,
            "parametric_distribution" : {
                "utterance_level" : 0,
                "decision_making_capability" : 3,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 0
            }
        },

        "ESQR" : {
            "avg_score" : 4,
            "parametric_distribution" : {
                "utterance_level" : none,
                "decision_making_capability" : 4,
                "retioconation_level" : none,
                "quantitative_ability_and_data_interpretation" : 0
            }
        }
    }

}