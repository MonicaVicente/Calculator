package com.inaer.calculator.client;


import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Calculator implements EntryPoint {
	private ContentPanel panelMain;
	private ContentPanel panelRow1;
	private ContentPanel panelRow2;
	private ContentPanel panelRow3;
	private ContentPanel panelRow4;
	private ContentPanel panelRow5;
	
	private TextButton buttonC;
	private TextButton buttonCE;
	private TextButton button0;
	private TextButton button1;
	private TextButton button2;
	private TextButton button3;
	private TextButton button4;
	private TextButton button5;
	private TextButton button6;
	private TextButton button7;
	private TextButton button8;
	private TextButton button9;
	private TextButton buttonA;
	private TextButton buttonM;
	private TextButton buttonP;
	private TextButton buttonD;
	private TextButton buttonR;
	private TextButton buttonPo;
	private TextButton buttonS;
	private TextButton buttonX;
	private TextButton buttonBin;
	private TextBox textBox;
	
	private float Accum = 0;
	private boolean FlagNewNum = false;
	private String PendingOp = "";

	private final CalcServiceAsync calcService = GWT.create(CalcService.class);
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		CenterLayoutContainer clc = new CenterLayoutContainer();
		clc.add(panelMain());
		RootPanel.get().add(clc);
	}
	
	private ContentPanel panelMain(){
		if(panelMain == null){
			VerticalLayoutContainer vlc = new VerticalLayoutContainer();
			vlc.add(panelRow1(), new VerticalLayoutData(450, 40, new Margins(1)));
			vlc.add(panelRow2(), new VerticalLayoutData(450, 25, new Margins(1)));
			vlc.add(panelRow3(), new VerticalLayoutData(450, 25, new Margins(1)));
			vlc.add(panelRow4(), new VerticalLayoutData(450, 25, new Margins(1)));
			vlc.add(panelRow5(), new VerticalLayoutData(450, 25, new Margins(1)));
			
			panelMain = new ContentPanel();
			panelMain.setHeading("Inaer calculator");
			panelMain.setHeight(300);
			panelMain.setWidth(415);
			panelMain.setBorders(true);
			panelMain.add(vlc);
		}
		return panelMain;
	}
	
	private ContentPanel panelRow1(){
		if(panelRow1 == null){
			HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
			hlc.add(textBox(), new HorizontalLayoutData(248, 30, new Margins(3)));
			hlc.add(buttonC(), new HorizontalLayoutData(80, 40, new Margins(3)));
			hlc.add(buttonCE(), new HorizontalLayoutData(80, 40, new Margins(3)));
			
			panelRow1 = new ContentPanel();
			panelRow1.setHeaderVisible(false);
			panelRow1.add(hlc);
		}
		return panelRow1;
	}

	private ContentPanel panelRow2(){
		if(panelRow2 == null){
			panelRow2 = new ContentPanel();
			panelRow2.setHeaderVisible(false);
			panelRow2.setButtonAlign(BoxLayoutPack.START);
			panelRow2.addButton(button7());
			panelRow2.addButton(button8());
			panelRow2.addButton(button9());
			panelRow2.addButton(buttonS());
			panelRow2.addButton(buttonPo());
		}
		return panelRow2;
	}
	
	private ContentPanel panelRow3(){
		if(panelRow3 == null){
			panelRow3 = new ContentPanel();
			panelRow3.setHeaderVisible(false);
			panelRow3.setButtonAlign(BoxLayoutPack.START);
			panelRow3.addButton(button4());
			panelRow3.addButton(button5());
			panelRow3.addButton(button6());
			panelRow3.addButton(buttonA());
			panelRow3.addButton(buttonM());
		}
		return panelRow3;
	}
	
	private ContentPanel panelRow4(){
		if(panelRow4 == null){
			panelRow4 = new ContentPanel();
			panelRow4.setHeaderVisible(false);
			panelRow4.setButtonAlign(BoxLayoutPack.START);
			panelRow4.addButton(button1());
			panelRow4.addButton(button2());
			panelRow4.addButton(button3());
			panelRow4.addButton(buttonP());
			panelRow4.addButton(buttonD());
		}
		return panelRow4;
	}
	
	private ContentPanel panelRow5(){
		if(panelRow5 == null){
			panelRow5 = new ContentPanel();
			panelRow5.setHeaderVisible(false);
			panelRow5.setButtonAlign(BoxLayoutPack.START);
			panelRow5.addButton(button0());
			panelRow5.addButton(buttonX());
			panelRow5.addButton(buttonBin());
			panelRow5.addButton(buttonR());
		}
		return panelRow5;
	}
	
	private TextBox textBox(){
		if(textBox == null){
			textBox = new TextBox();
			textBox.setAlignment(TextAlignment.RIGHT);
			textBox.setEnabled(false);
			textBox().setValue("0");
		}
		return textBox;
	}
	
	private TextButton buttonC(){
		if(buttonC == null){
			buttonC = new TextButton();
			buttonC.setText("C");
			buttonC.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Clear();
				}
			});
		}
		return buttonC;
	}
	
	private TextButton buttonCE(){
		if(buttonCE == null){
			buttonCE = new TextButton();
			buttonCE.setText("CE");
			buttonC.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					ClearEntry();
				}
			});
		}
		return buttonCE;
	}
	
	private TextButton button7(){
		if(button7 == null){
			button7 = new TextButton();
			button7.setText("7");
			button7.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(7);
				}
			});
		}
		return button7;
	}
	
	private TextButton button8(){
		if(button8 == null){
			button8 = new TextButton();
			button8.setText("8");
			button8.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(8);
				}
			});
		}
		return button8;
	}
	
	private TextButton button9(){
		if(button9 == null){
			button9 = new TextButton();
			button9.setText("9");
			button9.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(9);
				}
			});
		}
		return button9;
	}
	
	private TextButton buttonS(){
		if(buttonS == null){
			buttonS = new TextButton();
			buttonS.setText("+/-");
			buttonS.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Neg();
				}
			});
		}
		return buttonS;
	}
	
	private TextButton buttonPo(){
		if(buttonPo == null){
			buttonPo = new TextButton();
			buttonPo.setText("%");
			buttonPo.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Percent();
				}
			});
		}
		return buttonPo;
	}
	
	private TextButton button4(){
		if(button4 == null){
			button4 = new TextButton();
			button4.setText("4");
			button4.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(4);
				}
			});
		}
		return button4;
	}
	
	private TextButton button5(){
		if(button5 == null){
			button5 = new TextButton();
			button5.setText("5");
			button5.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(5);
				}
			});
		}
		return button5;
	}
	
	private TextButton button6(){
		if(button6 == null){
			button6 = new TextButton();
			button6.setText("6");
			button6.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(6);
				}
			});
		}
		return button6;
	}
	
	private TextButton buttonA(){
		if(buttonA == null){
			buttonA = new TextButton();
			buttonA.setText("+");
			buttonA.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Operation("+");
				}
			});
		}
		return buttonA;
	}
	
	private TextButton buttonM(){
		if(buttonM == null){
			buttonM = new TextButton();
			buttonM.setText("-");
			buttonM.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Operation("-");
				}
			});
		}
		return buttonM;
	}
	
	private TextButton button1(){
		if(button1 == null){
			button1 = new TextButton();
			button1.setText("1");
			button1.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(1);
				}
			});
		}
		return button1;
	}
	
	private TextButton button2(){
		if(button2 == null){
			button2 = new TextButton();
			button2.setText("2");
			button2.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(2);
				}
			});
		}
		return button2;
	}
	
	private TextButton button3(){
		if(button3 == null){
			button3 = new TextButton();
			button3.setText("3");
			button3.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(3);
				}
			});
		}
		return button3;
	}
	
	private TextButton buttonP(){
		if(buttonP == null){
			buttonP = new TextButton();
			buttonP.setText("*");
			buttonP.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Operation("*");
				}
			});
		}
		return buttonP;
	}
	
	private TextButton buttonD(){
		if(buttonD == null){
			buttonD = new TextButton();
			buttonD.setText("/");
			buttonD.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Operation("/");
				}
			});
		}
		return buttonD;
	}
	
	private TextButton button0(){
		if(button0 == null){
			button0 = new TextButton();
			button0.setText("0");
			button0.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					NumPressed(0);
				}
			});
		}
		return button0;
	}
	
	private TextButton buttonX(){
		if(buttonX == null){
			buttonX = new TextButton();
			buttonX.setText(".");
			buttonX.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Decimal();
				}
			});
		}
		return buttonX;
	}
	
	private TextButton buttonR(){
		if(buttonR == null){
			buttonR = new TextButton();
			buttonR.setText("=");
			buttonR.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Operation("=");
				}
			});
		}
		return buttonR;
	}
	
	private TextButton buttonBin(){
		if(buttonBin == null){
			buttonBin = new TextButton();
			buttonBin.setText("Bin");
			buttonBin.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					Binary();
				}
			});
		}
		return buttonBin;
	}
	
	private void NumPressed(int Num){
		if (FlagNewNum){
			textBox().setValue(String.valueOf(Num));
			FlagNewNum = false;
		}else {
			if (textBox().getValue().equals("0")){
				textBox().setValue(String.valueOf(Num));
			}else{
				textBox().setValue(textBox().getValue()+Num);
			}
		}
	}

	private void Operation(String Op){
		float result = Float.valueOf(textBox().getValue()).floatValue();
		if (FlagNewNum && !PendingOp.equals("="))
			;
		else {
			FlagNewNum = true;
			if (PendingOp.equals("+"))
				Accum += result;
			else if (PendingOp.equals("-"))
				Accum -= result;
			else if (PendingOp.equals("/"))
				Accum /= result;
			else if (PendingOp.equals("*"))
				Accum *= result;
			else
				Accum = result;
			textBox().setValue(String.valueOf(Accum));
			PendingOp = Op;
		}
	}
	
	private void Decimal(){
		String result = textBox().getValue();
		if (FlagNewNum){
			result = "0.";
			FlagNewNum = false;
		}
		else{
			if (result.indexOf(".") == -1)
				result = result + ".";
		}
		textBox().setValue(result);
	}
	
	private void ClearEntry(){
		textBox().setValue("0");
		FlagNewNum = true;
	}

	private void Clear(){
		Accum = 0;
		PendingOp = "";
		ClearEntry();
	}

	private void Neg(){
		float result = Float.valueOf(textBox().getValue()).floatValue();
		textBox().setValue(String.valueOf(result*-1));
	}

	private void Percent(){
		float result = Float.valueOf(textBox().getValue()).floatValue();
		textBox().setValue(String.valueOf((result/100)*Accum));
	}
	
	private void Binary(){
		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending number to the server:</b>" + textBox().getValue()));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		calcService.calcServer(textBox().getValue(), new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
				dialogBox.center();
				closeButton.setFocus(true);
			}

			public void onSuccess(String[] result) {
				textBox().setValue(result[0]);
				dialogBox.setText("Remote Procedure Call");
				serverResponseLabel.removeStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML((String)result[1]);
				dialogBox.center();
				closeButton.setFocus(true);
			}
		});
	}
}
