package treicco.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Treicco implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	// private static final String SERVER_ERROR = "An error occurred while " +
	// "attempting to contact the server. Please check your network " +
	// "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final TaskManagerAsync competitionManager = GWT.create(TaskManager.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PX);
		RootLayoutPanel.get().add(mainPanel);

		final LayoutPanel titlePanel = new LayoutPanel();
		mainPanel.addNorth(titlePanel, 100);

		final Label titleText = new Label("Task Repository for Informatics and Coding Competitions");
		titlePanel.add(titleText);

		// final StackLayoutPanel competitionPanel = new
		// StackLayoutPanel(Unit.PX);
		// mainPanel.addWest(competitionPanel, 250);

		// final Directory d = new Directory("/", "IOI");
		//
		// competitionManager.add(d, new AsyncCallback<Void>() {
		// public void onFailure(Throwable caught) {
		// addText.setText("add() exception: " + caught.toString());
		// }
		//
		// public void onSuccess(Void v) {
		// addText.setText("add () completed successfully: '" + d.getId() +
		// "'");
		// }
		// });

		// Add it to the root panel.
		mainPanel.addWest(new DirectoryPanel(), 250);

		// History.newItem("/");

		mainPanel.add(new MainPanel());

		competitionManager.init(new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}
		});

		// competitionManager.reset(new AsyncCallback<Void>() {
		// public void onFailure(Throwable caught) {
		// // TODO Auto-generated method stub
		//				
		// }
		//			
		// public void onSuccess(Void result) {
		// // TODO Auto-generated method stub
		//				
		// }
		// });

		// competitionManager.get("/IOI/", new AsyncCallback<Directory>() {
		// public void onFailure(Throwable caught) {
		// getText.setText("get() exception: " + caught.toString());
		// }
		//
		// public void onSuccess(Directory result) {
		// getText.setText("get() completed successfully: " + result.getId() +
		// " " + result.getDirectories().size());
		// item.setWidget(new HTML(result.getPath() + "  " + result.getName()));
		// }
		// });

		mainPanel.setStyleName("RootPanel");

		// final DialogBox addDialog = new DialogBox();
		// final Button addButton = new Button("Add");
		// final Button cancelButton = new Button("Cancel");
		// TextBox addTBox = new TextBox();

		// HorizontalPanel addHPanel = new HorizontalPanel();
		// addHPanel.add(addButton);
		// addHPanel.add(cancelButton);

		// final Button sendButton = new Button("Send");
		// final TextBox nameField = new TextBox();
		// nameField.setText("GWT User");
		// final Label errorLabel = new Label();
		//
		// // We can add style names to widgets
		// sendButton.addStyleName("sendButton");
		//
		// // Add the nameField and sendButton to the RootPanel
		// // Use RootPanel.get() to get the entire body element
		// RootPanel.get("nameFieldContainer").add(nameField);
		// RootPanel.get("sendButtonContainer").add(sendButton);
		// RootPanel.get("errorLabelContainer").add(errorLabel);
		//
		// // Focus the cursor on the name field when the app loads
		// nameField.setFocus(true);
		// nameField.selectAll();
		//
		// // Create the popup dialog box
		// final DialogBox dialogBox = new DialogBox();
		// dialogBox.setText("Remote Procedure Call");
		// dialogBox.setAnimationEnabled(true);
		// final Button closeButton = new Button("Close");
		// // We can set the id of a widget by accessing its Element
		// closeButton.getElement().setId("closeButton");
		// final Label textToServerLabel = new Label();
		// final HTML serverResponseLabel = new HTML();
		// VerticalPanel dialogVPanel = new VerticalPanel();
		// dialogVPanel.addStyleName("dialogVPanel");
		// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		// dialogVPanel.add(textToServerLabel);
		// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		// dialogVPanel.add(serverResponseLabel);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		// dialogVPanel.add(closeButton);
		// dialogBox.setWidget(dialogVPanel);
		//
		// // Add a handler to close the DialogBox
		// closeButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// dialogBox.hide();
		// sendButton.setEnabled(true);
		// sendButton.setFocus(true);
		// }
		// });
		//
		// // Create a handler for the sendButton and nameField
		// class MyHandler implements ClickHandler, KeyUpHandler {
		// /**
		// * Fired when the user clicks on the sendButton.
		// */
		// public void onClick(ClickEvent event) {
		// sendNameToServer();
		// }
		//
		// /**
		// * Fired when the user types in the nameField.
		// */
		// public void onKeyUp(KeyUpEvent event) {
		// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		// sendNameToServer();
		// }
		// }
		//
		// /**
		// * Send the name from the nameField to the server and wait for a
		// * response.
		// */
		// private void sendNameToServer() {
		// // First, we validate the input.
		// errorLabel.setText("");
		// String textToServer = nameField.getText();
		// if (!FieldVerifier.isValidName(textToServer)) {
		// errorLabel.setText("Please enter at least four characters");
		// return;
		// }
		//
		// // Then, we send the input to the server.
		// sendButton.setEnabled(false);
		// textToServerLabel.setText(textToServer);
		// serverResponseLabel.setText("");
		// greetingService.greetServer(textToServer,
		// new AsyncCallback<String>() {
		// public void onFailure(Throwable caught) {
		// // Show the RPC error message to the user
		// dialogBox
		// .setText("Remote Procedure Call - Failure");
		// serverResponseLabel
		// .addStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(SERVER_ERROR);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		//
		// public void onSuccess(String result) {
		// dialogBox.setText("Remote Procedure Call");
		// serverResponseLabel
		// .removeStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(result);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		// });
		// }
		// }
		//
		// // Add a handler to send the name to the server
		// MyHandler handler = new MyHandler();
		// sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);

		if (History.getToken().length() == 0) {
			History.newItem("/");
		} else {
			History.fireCurrentHistoryState();
		}
	}
}
