package de.firstglobalgermany.fgcscoringapp;

import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	CountDownTimer countdown;
	long time;

	TextView timeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		timeView = findViewById(R.id.vTime);
		countdown = createTimer();
	}

	public CountDownTimer createTimer(){
		return new CountDownTimer(150000, 1000) {
			@Override
			public void onTick(long l) {
				int minutes = (int)Math.floor(l/1000/60);
				int seconds = (int)(l-minutes*1000*60)/1000;
				timeView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
			}

			@Override
			public void onFinish() {
				timeView.setTextColor(Color.RED);
			}
		};
	}

	public void start(View v){
		countdown.start();
	}
	/*
	int solarPanels = 0;
	long[] solarPanelTimes = new long[5];
	boolean windTurbine = false;
	long windTurbineTime = 0;
	boolean reactionPlant = false;
	long reactionPlantTime = 0;
	int combustionPlantLow = 0;
	int combustionPlantHigh = 0;
	boolean coopertition = false;
	int parking = 0;
	Chronometer chronometer;

	int totalScore = 0;

	TextView scoreText;
	TextView solarText;
	TextView windText;
	TextView reactionText;
	TextView combustionLowText;
	TextView combustionHighText;
	TextView coopertitionText;
	TextView parkingText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		chronometer = (Chronometer)findViewById(R.id.chronometer);

		solarText = findViewById(R.id.infSolar);
		windText = findViewById(R.id.infTurbine);
		reactionText = findViewById(R.id.infReaction);
		combustionLowText = findViewById(R.id.infCombustionLow);
		combustionHighText = findViewById(R.id.infCombustionHigh);
		coopertitionText = findViewById(R.id.infCoopertition);
		parkingText = findViewById(R.id.infParking);
		scoreText = findViewById(R.id.infScore);
	}

	public void startGame(View v){
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
	}

	public void scoreSolar(View v){
		if(solarPanels < 5){
			solarPanelTimes[solarPanels] = 150000 - (SystemClock.elapsedRealtime() - chronometer.getBase());
			solarPanels++;

			solarText.setText(Integer.toString(solarPanels));
			showScore(v);
		}
	}

	public void scoreWind(View v){
		if(windTurbine == false){
			windTurbineTime = 150000 - (SystemClock.elapsedRealtime() - chronometer.getBase());
			windTurbine = true;
			showScore(v);

			windText.setText(Long.toString(windTurbineTime/1000));
		}
	}

	public void scoreReaction(View v){
		if(reactionPlant == false){
			reactionPlantTime = 150000 - (SystemClock.elapsedRealtime() - chronometer.getBase());
			reactionPlant = true;
			showScore(v);

			reactionText.setText(Long.toString(reactionPlantTime/1000));
		}
	}

	public void scoreCombustionLow(View v){
		combustionPlantLow+=1;
		showScore(v);

		combustionLowText.setText(Integer.toString(combustionPlantLow));
	}

	public void scoreCombustionHigh(View v){
		combustionPlantHigh+=1;
		showScore(v);

		combustionHighText.setText(Integer.toString(combustionPlantHigh));
	}

	public void scoreCoopertition(View v){
		coopertition = true;
		showScore(v);

		coopertitionText.setText("Yes");
	}

	public void scoreParking(View v){
		if(parking < 3){
			parking+=1;
			showScore(v);

			parkingText.setText(Integer.toString(parking));
		}
	}

	public void showScore(View v){
		totalScore = 0;

		int solarScore = 0;
		for(int i = 0; i<solarPanels; i++){
			solarScore += (solarPanelTimes[i]) * 0.001;
		}
		totalScore+=solarScore;

		int windScore = 0;
		if(windTurbine){
			windScore = (int)(Math.round(windTurbineTime) * 0.001);
		}
		totalScore+=windScore;

		int reactionScore = 0;
		if(reactionPlant){
			reactionScore = (int)(reactionPlantTime * 0.003);
		}
		totalScore+=reactionScore;

		int combustionScore = combustionPlantLow * 5 + combustionPlantHigh * 20;
		totalScore+=combustionScore;

		if(coopertition){
			totalScore+=100;
		}

		int parkingScore=parking*15;
		if(parking == 3){
			parkingScore += 5;
		}
		totalScore+=parkingScore;

		scoreText.setText(Integer.toString(totalScore));
	}

	public void reset(View v) {
		chronometer.stop();
		chronometer.setBase(SystemClock.elapsedRealtime());

		solarPanels = 0;
		windTurbine = false;
		reactionPlant = false;
		combustionPlantLow = 0;
		combustionPlantHigh = 0;
		coopertition = false;
		parking = 0;
		totalScore = 0;

		solarText.setText("");
		windText.setText("");
		reactionText.setText("");
		combustionLowText.setText("");
		combustionHighText.setText("");
		coopertitionText.setText("");
		parkingText.setText("");
		scoreText.setText("");
	}
	*/
}
