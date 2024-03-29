package com.kocci.healtikuy.core.util.helper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


//? 2 types of category : info & tips.
@Parcelize
data class Tips(
    val category: String,
    val content: String,
) : Parcelable

@Parcelize
data class TipList(
    val list: List<Tips>
) : Parcelable

object TipsManager {
    fun generateSleepTips(): TipList = TipList(
        listOf<Tips>(
            Tips(
                "tips",
                "We all have heard that 8 hours of sleep is a good sleep duration. Meanwhile it was true, but optimal Sleep time for people is actually different by age. National Sleep Foundation recommends that Young adults (14-17 Years) have at least 8 - 10 hours duration of sleep. Meanwhile, for adults, it should be 7 - 9 hours sleep. However, the optimal sleep time for some people might be different, because of many factor."
            ),
            Tips(
                "tips",
                """
                  Consistent Sleep promotes better health. It also have many benefits for you if you sticking with your schedule, and that is : 
                  - Lowering your stress
                  - Improving moods
                  - Making it easy for you to sleep in your schedule 
                  - Feel refreshed in the morning
                  - And many more
                """.trimIndent()
            ),
            Tips(
                "tips",
                """
                  Here's how to fix your sleep schedule : 
                  - Find the right bedtime: set aside enough time each night.
                  - Stick to schedule : try to always stick on your sleep schedule (bedtime and waketime), including on weekends.
                  - Get some exercise : Doing regular exercise helps you sleep better, but avoid having vigorous physical activity just before bedtime.
                  - Turn off electronic devices : Don't use electronic devices such as smartphone, when you trying to sleep. 
                  - Be careful with caffeine : Don't drink coffee near your bedtime.
                """.trimIndent()
            ),
            Tips(
                "info",
                "Inconsistent of sleep time and later sleep have adverse impact on health. Better to avoid it."
            ),
            Tips(
                "info",
                "Consistent sleep is when you have the same wake and sleep time everyday, even on holidays and weekends. Example : Always sleep in +- 10PM and wake up in +- 6AM everyday."
            ),
        )
    )

    fun generateExerciseTips() = TipList(
        listOf<Tips>(
            Tips(
                "info",
                "World Health Organization (WHO) defines physical activity as any bodily movement produced by skeletal muscles that requires energy expenditure. Walking, Cycling, is also physical activity. However, it's a good idea to spare a time to do a regular exercise, like Jogging to be example."
            ),
            Tips(
                "info",
                """
                    There's much benefits for us if we have a regular physical activity / exercise, that is : 
                    - Help maintain a healthy body weight
                    - Improve bone and functional health
                    - Reduces symptoms of depression and anxiety
                    - Enhances thinking, learning, and judgment skills
                    - Reduce the risk of hypertension, coronary heart disease, stroke, diabetes, various types of cancer (including breast cancer and colon cancer), and depression
                    - And many more
                """.trimIndent()
            ),
            Tips(
                "tips",
                "World Health Organization (WHO) recommend that For Children and Adolescent (aged 5-17 years), should do at least 60 minutes per day physical activity, across the week."
            ),
            Tips(
                "tips",
                "For Adults (aged 18-64 years), should do at least 150-300 minutes of moderate-intensity, or at least 75-150 minutes of vigorous-intensity aerobic physical activity, throughout a week."
            )
        )
    )

    fun generateNutritionTips() = TipList(
        listOf<Tips>(
            Tips(
                "tips",
                "Avoid too much saturated fat. On average, men should have no more 30g of saturated fat per day. 20g for women. Too much saturated fat can increase the amount of cholesterol."
            ),
            Tips(
                "tips",
                "Avoid too much sugar. Regularly consuming it can increase your risk of obesity and tooth decay."
            ),
            Tips(
                "tips",
                "eat less salt, no more than 6g a day for adults. Eating too much salt can raise your blood pressure, which lead to many health problem."
            ),
            Tips(
                "tips",
                "Base your meals on higher fibre starchy carbohydrates, such as potatoes, bread, cereal, pasta and rice. It can help you feel full for longer."
            ),
            Tips(
                "tips",
                "Eat lots of fruit and vegetables. It's recommended that you eat at least 5 portions of a variety of fruit and veg every day. They can be fresh, frozen, canned, dried or juiced."
            ),
            Tips(
                "tips",
                "Eat more fish, including a portion of oily fish. Fish is a good source of protein and contain many vitamin and minerals."
            ),
            Tips(
                "info",
                """
                    There are 2 types of fat : saturated and unsaturated fat. 
                    Eating too much saturated fat, can increase the amount of cholesterol in the blood, 
                    which increase the risk of heart disease and another disease.
                """.trimIndent()
            ),
            Tips(
                "info",
                """
                    Sugary food or drinks are often high in energy (measured by calories), and if consumed too much can lead to weight gain and also tooth decay.
                    Free sugar is what we call sugar added to a food or drink, or found naturally in honey, syrups, and smoothies.
                    This is the type of sugar that we should avoid, rather than sugar found in fruit and milk.
                    Free sugar are found in many food such as : 
                    - sugary fizzy drinks
                    - sugary breakfast cereals
                    - cakes
                    - biscuits
                    - pastries and puddings
                    - sweets and chocolate
                    - alcoholic drinks
                """.trimIndent()
            )
        )
    )

    fun generateSunExposureTips() = TipList(
        listOf<Tips>(
            Tips(
                "tips",
                "Avoid having too much sun exposure. The optimal duration was 5-15 minutes."
            ),
            Tips(
                "tips",
                "You can apply sunblock if you want. However, if you wanna sunbathe in more than 15 minutes, apply sunblock is a must."
            ),
            Tips(
                "info",
                "Sun Exposure has many benefit, such as vitamin D and promote better body immunity. However, avoid having too much sun exposure. Stop when you feels like your skin burn."
            ),
            Tips(
                "info",
                """
                    Here's the benefit if you have enough sun exposure : 
                    - Increase Vitamin D :  Vitamin D has some important functions in the body. It promotes reduced inflammation and modulates cell growth. Sunlight was a natural source of vitamin D
                    - Improve Mood 
                    - Higher Quality Sleep
                    - Stronger Bones 
                    - Lower Blood Pressure
                """.trimIndent()
            )
        )
    )
}