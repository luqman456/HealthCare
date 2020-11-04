package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.healthcare.Adapter.LinksListAdapter;

public class HealthLinkActivity extends AppCompatActivity {
    String[] titles = {"10 ways to control high blood pressure without medication", "17 Effective Ways to Lower Your Blood Pressure",
            "15 natural ways to lower your blood pressure", "Ways to Lower Blood Pressure", "6 simple tips to reduce your blood pressure",
            "15 Easy Ways to Lower Blood Sugar Levels Naturally","Emergency Highs: How to Lower Blood Sugar Quickly",
            "Which foods lower blood sugar?","How can you lower your blood sugar levels?","So You Went on a Sugar Bender — Now What?"
    };

    String[] descriptions = {
            "If you've been diagnosed with high blood pressure, you might be worried about taking medication to bring your numbers down.", "Your blood pressure depends on how much blood your heart is pumping, and how much resistance there is to blood flow in your arteries.",
            "High blood pressure is a dangerous condition that can damage your heart. It affects one in three people in the US and 1 billion people worldwide (1, 2).",
            "High blood pressure raises your chances of having both heart attack and stroke. It’s important to know your numbers, because if you have high blood pressure you’ll want to bring those numbers down.",
            "If you suddenly find yourself with high blood pressure (hypertension) under the new guidelines from the American Heart Association and the American College of Cardiology, you might be wondering what to do.",

            "Increased insulin sensitivity means your cells are better able to use the available sugar in your bloodstream.","When your blood sugar level gets too high — known as hyperglycemia or high blood glucose — the quickest way to reduce it is to take fast-acting insulin.",
 "When a person has diabetes, either their body does not produce enough insulin, or it cannot use the insulin correctly, so glucose accumulates in the blood.",
         "Blood sugar levels are a primary concern for people with diabetes. High blood sugar, known as hyperglycemia, occurs when a person’s blood sugar is over 180 milligrams per deciliter (mg/dL).",
            "Sometimes the cause of a blood sugar spike is clear (“Yeah, I’ll have two more shots of tequila! Actually, just put it on my ice cream!”). But other times, the cause is a little more mysterious."

    };

    String[] links = {
            "https://www.mayoclinic.org/diseases-conditions/high-blood-pressure/in-depth/high-blood-pressure/art-20046974", "https://www.healthline.com/health/high-blood-pressure-hypertension/lower-it-fast",
            "https://www.medicalnewstoday.com/articles/318716", "https://www.webmd.com/hypertension-high-blood-pressure/how-to-lower-blood-pressure#1",
            "https://www.health.harvard.edu/staying-healthy/6-simple-tips-to-reduce-your-blood-pressure",

            "https://www.healthline.com/nutrition/15-ways-to-lower-blood-sugar#1","https://www.healthline.com/health/diabetes/how-to-lower-blood-sugar-quickly-emergency",
            "https://www.medicalnewstoday.com/articles/322861","https://www.medicalnewstoday.com/articles/320738",
            "https://greatist.com/health/how-to-lower-blood-sugar"

    };
    ListView list;
    LinksListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_link);
        adapter=new LinksListAdapter(this, titles, descriptions,links);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}