package treicco.client;

import java.util.Set;

import treicco.shared.CompetitionSyntax;
import treicco.shared.DirectoryProxy;
import treicco.shared.DirectoryRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class DirectoryWritePanel extends Composite implements Editor<DirectoryProxy> {

	private static DirectoryWritePanelUiBinder uiBinder = GWT.create(DirectoryWritePanelUiBinder.class);

	interface DirectoryWritePanelUiBinder extends UiBinder<Widget, DirectoryWritePanel> {
	}

	private static DirectoryWritePanelEditorDriver editorDriver;

	interface DirectoryWritePanelEditorDriver extends RequestFactoryEditorDriver<DirectoryProxy, DirectoryWritePanel> {
	}

	interface MyStyle extends CssResource {
		String Writable();

		String Readable();
	}

	@UiField
	TextBox shortName;

	@UiField
	TextBox fullName;

	@UiField
	TextBox location;

	@UiField
	TextBox website;

	@UiField
	FlowPanel fields;

	@UiField
	MyStyle style;

	public DirectoryWritePanel() {
		initWidget(uiBinder.createAndBindUi(this));

		fields.addStyleName(style.Writable());

		setPath(History.getToken());
	}

	public void setPath(String targetPath) {
		if (targetPath.equals("/"))
			return;

		editorDriver = GWT.create(DirectoryWritePanelEditorDriver.class);
		editorDriver.initialize(Treicco.requestFactory, this);

		// The request is configured arbitrarily
		Request<DirectoryProxy> fetchRequest = Treicco.requestFactory.directoryRequest().findDirectory(CompetitionSyntax.extractParent(targetPath), CompetitionSyntax.extractCodeName(targetPath));

		// Add the paths that the EditorDrives computes
		fetchRequest.with(editorDriver.getPaths());

		// We could do more with the request, but we just fire it
		fetchRequest.to(new Receiver<DirectoryProxy>() {
			@Override
			public void onSuccess(DirectoryProxy response) {
				// PersonEditorWorkflow.this.person = person;
				// Start the edit process
				DirectoryRequest context = Treicco.requestFactory.directoryRequest();
				// Display the UI
				editorDriver = GWT.create(DirectoryWritePanelEditorDriver.class);
				editorDriver.initialize(Treicco.requestFactory, DirectoryWritePanel.this);
				editorDriver.edit(response, context);
				// Configure the method invocation to be sent in the context
				context.update().using(response);
				// The context will be fire()'ed from the onSave() method
			}
		}).fire();
	}

	@UiHandler("commit")
	public void commitChanges(ClickEvent e) {
		// Flush the contents of the UI
		RequestContext context = editorDriver.flush();

		// Check for errors
		if (editorDriver.hasErrors()) {
			GWT.log("Errors detected locally");
			return;
		}

		// Send the request
		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				// If everything went as planned, just dismiss the dialog box
				GWT.log("Everything went on fine");
				fields.removeStyleName(style.Writable());
				fields.addStyleName(style.Readable());
				shortName.setReadOnly(true);
			}

			@Override
			public void onViolation(Set<Violation> errors) {
				// Otherwise, show ConstraintViolations in the UI
				GWT.log("Errors detected on the server");
				editorDriver.setViolations(errors);
			}
		});
	}
}
