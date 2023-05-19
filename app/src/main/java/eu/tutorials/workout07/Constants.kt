package eu.tutorials.workout07

object Constants {
     fun defaultExerciseList():ArrayList<exerciseModel> {
         val exerciselist = ArrayList<exerciseModel>()
         val jumping_jacks = exerciseModel(
             1, "Jumping Jacks",
             R.drawable.ic_jumpingjacks,
             false,
             false
         )
         exerciselist.add(jumping_jacks)
         val wall_sit = exerciseModel(
             2, "Wall Sit",
             R.drawable.ic_wallsit,
             false,
             false
         )
         exerciselist.add(wall_sit)
         val push_up = exerciseModel(
             3, "Push Ups",
             R.drawable.ic_pushup,
             false,
             false
         )
         exerciselist.add(push_up)
         val abd_crunches = exerciseModel(
             4, "Abdominal Crunches",
             R.drawable.ic_crunches,
             false,
             false
         )
         exerciselist.add(abd_crunches)
         val step_up = exerciseModel(
             5, "Step Up On Chair",
             R.drawable.ic_stepup,
             false,
             false
         )
         exerciselist.add(step_up)
         val squat= exerciseModel(
             6, "Squat",
             R.drawable.ic_squat,
             false,
             false
         )
         exerciselist.add(squat)
         val tricep_dip= exerciseModel(
             7, "Triceps Dip",
             R.drawable.ic_tricep_dip,
             false,
             false
         )
         exerciselist.add(tricep_dip)
         val pushup_rotation= exerciseModel(
             8, "Push Ups And Rotations",
             R.drawable.ic_pushup_rotation,
             false,
             false
         )
         exerciselist.add(pushup_rotation)
         val side_plank= exerciseModel(
             9, "Side Plank",
             R.drawable.ic_side_plank,
             false,
             false
         )
         exerciselist.add(side_plank)
         val High_knees= exerciseModel(
             10, "High Knees On Running",
             R.drawable.ic_highknees,
             false,
             false
         )
         exerciselist.add(High_knees)

         return exerciselist
     }

}