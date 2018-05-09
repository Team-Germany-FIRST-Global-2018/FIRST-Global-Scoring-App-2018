package de.firstglobalgermany.fgcscoringapp;

import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	CountDownTimer countdown;
	long time;

	TextView timeView;
	Button[] solarButtons = new Button[5];
	TextView[] solarViews = new TextView[5];
	int[] solarTime = new int[5];

	TextView windView;
	Button windButton;
	int windTime = 0;

	TextView reactionView;
	Button reactionButton;
	int reactionTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		timeView = findViewById(R.id.vTime);
		getSolars();
		windView = findViewById(R.id.vWind);
		windButton = findViewById(R.id.bWind);
		reactionView = findViewById(R.id.vReaction);
		reactionButton = findViewById(R.id.bReaction);

		countdown = createTimer();
	}

	private CountDownTimer createTimer(){
		return new CountDownTimer(150000, 1000) {
			@Override
			public void onTick(long l) {
				int minutes = (int)Math.floor(l/1000/60);
				int seconds = (int)(l-minutes*1000*60)/1000;
				time = l;
				timeView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
			}

			@Override
			public void onFinish() {
				timeView.setText("0:00");
				timeView.setTextColor(Color.RED);
			}
		};
	}

	private void getSolars(){
		solarButtons[0] = findViewById(R.id.bSolar0);
		solarButtons[1] = findViewById(R.id.bSolar1);
		solarButtons[2] = findViewById(R.id.bSolar2);
		solarButtons[3] = findViewById(R.id.bSolar3);
		solarButtons[4] = findViewById(R.id.bSolar4);

		solarViews[0] = findViewById(R.id.vSolar0);
		solarViews[1] = findViewById(R.id.vSolar1);
		solarViews[2] = findViewById(R.id.vSolar2);
		solarViews[3] = findViewById(R.id.vSolar3);
		solarViews[4] = findViewById(R.id.vSolar4);
	}

	private String getTimeString(long millisec){
		int minutes = (int)Math.floor(millisec/1000/60);
		int seconds = (int)(millisec - minutes*1000*60)/1000;
		String addition = "";
		if(seconds < 10){
			addition = "0";
		}
		return (Integer.toString(minutes)+":"+addition+Integer.toString(seconds));
	}
	public void start(View v){
		countdown.start();
	}

	public void scoreSolar(View v){
		int solarNum = 0;
		for(int i = 0; i<5; i++){
			if(v == solarButtons[i]){solarNum = i;}
		}
		if(solarTime[solarNum] == 0) {
			solarButtons[solarNum].setText("Unscore");
			solarViews[solarNum].setText(getTimeString(time));
			solarTime[solarNum] = (int)time/1000;
		} else {
			solarButtons[solarNum].setText("Solar");
			solarViews[solarNum].setText("0:00");
			solarTime[solarNum] = 0;
		}
	}

	public void scoreWind(View v){
		if(windTime == 0){
			windButton.setText("Unscore");
			windView.setText(getTimeString(time));
			windTime = (int)time/1000;
		} else {
			windButton.setText("Wind");
			windView.setText("0:00");
			windTime = 0;
		}
	}

	public void scoreReaction(View v){
		if(reactionTime == 0){
			reactionButton.setText("Unscore");
			reactionView.setText(getTimeString(time));
			reactionTime = (int)time/1000;
		} else {
			reactionButton.setText("Reaction");
			reactionView.setText("0:00");
			reactionTime = 0;
		}
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
