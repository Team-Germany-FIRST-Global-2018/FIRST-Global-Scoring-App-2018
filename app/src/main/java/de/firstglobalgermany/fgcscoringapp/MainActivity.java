package de.firstglobalgermany.fgcscoringapp;

import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	CountDownTimer countdown;
	long time;
	boolean running = false;

	TextView timeView;
	TextView scoreView;

	Button[] solarButtons = new Button[5];
	TextView[] solarViews = new TextView[5];
	int[] solarTime = new int[5];

	TextView windView;
	Button windButton;
	int windTime = 0;

	TextView reactionView;
	Button reactionButton;
	int reactionTime;

	TextView lowView;
	Button lowButton;
	Button lowUnscoreButton;
	int lowNum;

	TextView highView;
	Button highButton;
	Button highUnscoreButton;
	int highNum;

	TextView parkingView;
	int parkingNum;

	boolean coopertition = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		timeView = findViewById(R.id.vTime);
		scoreView = findViewById(R.id.vScore);
		getSolars();
		windView = findViewById(R.id.vWind);
		windButton = findViewById(R.id.bWind);
		reactionView = findViewById(R.id.vReaction);
		reactionButton = findViewById(R.id.bReaction);
		lowView = findViewById(R.id.vLow);
		highView = findViewById(R.id.vHigh);
		parkingView = findViewById(R.id.vParking);

		countdown = createTimer();
	}

	private CountDownTimer createTimer(){
		return new CountDownTimer(150000, 1000) {
			@Override
			public void onTick(long l) {
				int minutes = (int)Math.floor(l/1000/60);
				int seconds = (int)(l-minutes*1000*60)/1000;
				time = l;
				timeView.setText(getTimeString(l));
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
	public void startReset(View v){
		Button button = (Button)v;
		timeView.setTextColor(Color.BLACK);
		if(!running) {
			button.setText("Reset");
			button.setTextColor(Color.RED);
			running = true;
			countdown.start();
		} else {
			button.setText("Start");
			button.setTextColor(Color.GREEN);
			timeView.setText("2:30");
			countdown.cancel();
			running = false;
			//reset
		}
	}

	public void showScore(){
		int totalScore = 0;
		for(int i = 0; i<5; i++){
			totalScore+=solarTime[i];
		}
		totalScore+=windTime;
		totalScore+=reactionTime*3;
		totalScore+=lowNum*5;
		totalScore+=highNum*20;

		if(coopertition){
			totalScore+=100;
		}
		totalScore+=parkingNum*15;
		if(parkingNum==3){
			totalScore+=5;
		}
		scoreView.setText(Integer.toString(totalScore));
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
		showScore();
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
		showScore();
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
		showScore();
	}

	public void scoreLow(View v){
		lowNum++;
		lowView.setText(Integer.toString(lowNum));
		showScore();
	}

	public void unscoreLow(View v){
		if(lowNum > 0) {
			lowNum--;
			lowView.setText(Integer.toString(lowNum));
		}
		showScore();
	}

	public void scoreHigh(View v){
		highNum++;
		highView.setText(Integer.toString(highNum));
		showScore();
	}

	public void unscoreHigh(View v){
		if(highNum > 0) {
			highNum--;
			highView.setText(Integer.toString(highNum));
		}
		showScore();
	}

	public void scoreCoopertition(View v){
		Button button = (Button)v;
		if(!coopertition){
			coopertition = true;
			button.setTextColor(Color.GREEN);
		} else {
			coopertition = false;
			button.setTextColor(Color.BLACK);
		}
		showScore();
	}

	public void scoreParking(View v){
		if(parkingNum < 3) {
			parkingNum++;
			parkingView.setText(Integer.toString(parkingNum));
		}
		showScore();
	}

	public void unscoreParking(View v){
		if(parkingNum > 0) {
			parkingNum--;
			parkingView.setText(Integer.toString(parkingNum));
		}
		showScore();
	}


}
